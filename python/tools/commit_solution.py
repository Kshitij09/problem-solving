#!/usr/bin/env python3
"""Commits a solved problem with a consistent message.

    tools/commit_solution.py tuf/0138.find-the-repeating-and-missing-number
    tools/commit_solution.py tuf/0138.find-the-repeating-and-missing-number/solution_xor.py
    tools/commit_solution.py --dry-run --note "O(1) space with XOR" <path>

<path> is a problem directory (commits everything changed in it) or solution files in one
problem directory (commits those, plus testcases.txt if it changed). The message depends on
what the problem already has in git:

    Solve takeUforward 138                  first commit for the problem
    Add takeUforward 138 alternative: xor   new solution_<name>.py files
    Update takeUforward 138                 anything else

and the body names the problem and links to it, so titles stay short. Each solution being
committed is run first (its __main__ harness checks testcases.txt); a failure aborts the
commit unless --skip-tests.

Sites are registered in SITES by their directory, relative to the repo root. To add one, write
a parser that reads a problem directory into a Problem.
"""

from __future__ import annotations

import argparse
import re
import subprocess
import sys
from collections.abc import Callable
from dataclasses import dataclass
from pathlib import Path

SOLUTION_GLOB = "solution*.py"


@dataclass(frozen=True)
class Problem:
    site: str  # as written in commit titles, e.g. "takeUforward"
    number: str
    name: str
    url: str


# --- Sites -----------------------------------------------------------------------------------

TUF_HEADER_RE = re.compile(r"\A# (?P<number>\d+)\. (?P<name>.+)\n# (?P<url>https://\S+)\n")


def parse_tuf(problem_dir: Path) -> Problem:
    """Reads the two-line header `tuf pick` writes at the top of every solution file."""
    for solution in sorted(problem_dir.glob(SOLUTION_GLOB)):
        if m := TUF_HEADER_RE.match(solution.read_text()):
            return Problem("takeUforward", m["number"], m["name"].strip(), m["url"])
    raise ValueError(f"{problem_dir}: no solution file starts with a '# <n>. <name>' / '# <url>' header")


# Repo-relative directory holding one subdirectory per problem -> parser for those.
SITES: dict[str, Callable[[Path], Problem]] = {
    "python/tuf": parse_tuf,
}


# --- Message ---------------------------------------------------------------------------------


def alternative_name(path: str) -> str | None:
    """'solution_xor.py' -> 'xor'; None for solution.py and non-solutions."""
    m = re.fullmatch(r"solution_(\w+)\.py", Path(path).name)
    return m[1] if m else None


def commit_message(problem: Problem, is_new_problem: bool, added_files: list[str], note: str = "") -> str:
    """Builds the message; added_files are paths new to git in this commit."""
    label = f"{problem.site} {problem.number}"
    alternatives = sorted(n for f in added_files if (n := alternative_name(f)))
    if is_new_problem:
        title = f"Solve {label}"
    elif alternatives:
        noun = "alternative" if len(alternatives) == 1 else "alternatives"
        title = f"Add {label} {noun}: {', '.join(alternatives)}"
    else:
        title = f"Update {label}"

    paragraphs = [title, f"{problem.name}\n{problem.url}"]
    if note:
        paragraphs.append(note.strip())
    return "\n\n".join(paragraphs) + "\n"


# --- Git -------------------------------------------------------------------------------------


def git(root: Path, *args: str, stdin: str | None = None) -> str:
    result = subprocess.run(
        ["git", *args], cwd=root, input=stdin, capture_output=True, text=True, check=False
    )
    if result.returncode != 0:
        raise SystemExit(f"git {' '.join(args)} failed:\n{result.stderr.strip()}")
    return result.stdout


@dataclass(frozen=True)
class Change:
    path: str  # repo-relative
    added: bool  # new to git (untracked or newly staged)


def changes_in(root: Path, paths: list[str]) -> list[Change]:
    out = git(root, "status", "--porcelain=v1", "--untracked-files=all", "--", *paths)
    changes = []
    for line in out.splitlines():
        status, path = line[:2], line[3:]
        changes.append(Change(path, added=status in ("??", "A ", "AM")))
    return changes


def is_tracked(root: Path, directory: str) -> bool:
    return bool(git(root, "ls-tree", "-r", "--name-only", "HEAD", "--", directory).strip())


# --- Main ------------------------------------------------------------------------------------


def find_site(rel_dir: Path) -> Callable[[Path], Problem]:
    for site_dir, parser in SITES.items():
        if rel_dir.parent == Path(site_dir):
            return parser
    known = ", ".join(f"{d}/<problem>" for d in SITES)
    raise SystemExit(f"{rel_dir} isn't a problem directory; expected one of: {known}")


def resolve(root: Path, targets: list[str]) -> tuple[Path, list[str]]:
    """Returns the problem directory and the repo-relative paths to commit."""
    paths = [Path(t).resolve() for t in targets]
    problem_dirs = {p if p.is_dir() else p.parent for p in paths}
    if len(problem_dirs) != 1:
        raise SystemExit("give one problem directory, or solution files from a single problem")
    problem_dir = problem_dirs.pop()
    rel = lambda p: str(p.relative_to(root))
    if paths == [problem_dir]:
        return problem_dir, [rel(problem_dir)]
    return problem_dir, [rel(p) for p in paths] + [rel(problem_dir / "testcases.txt")]


def run_solutions(root: Path, files: list[str]) -> bool:
    ok = True
    for f in files:
        result = subprocess.run([sys.executable, f], cwd=root, capture_output=True, text=True)
        if result.returncode != 0:
            ok = False
            print(f"== {f} failed\n{result.stdout}{result.stderr}", file=sys.stderr)
    return ok


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(
        prog="commit_solution.py",
        description=__doc__,
        formatter_class=argparse.RawDescriptionHelpFormatter,
    )
    parser.add_argument("paths", nargs="+", help="a problem directory, or solution files in one")
    parser.add_argument("--note", default="", help="an extra paragraph for the message body")
    parser.add_argument("--dry-run", action="store_true", help="print the message and files only")
    parser.add_argument("--skip-tests", action="store_true", help="commit even if a solution fails")
    argv = sys.argv[1:] if argv is None else argv
    if not argv:
        parser.print_help()
        return 2
    args = parser.parse_args(argv)

    root = Path(git(Path.cwd(), "rev-parse", "--show-toplevel").strip())
    problem_dir, paths = resolve(root, args.paths)
    rel_dir = problem_dir.relative_to(root)
    problem = find_site(rel_dir)(problem_dir)

    changes = changes_in(root, paths)
    if not changes:
        raise SystemExit(f"nothing to commit in {', '.join(paths)}")
    files = [c.path for c in changes]
    message = commit_message(
        problem,
        is_new_problem=not is_tracked(root, str(rel_dir)),
        added_files=[c.path for c in changes if c.added],
        note=args.note,
    )

    if args.dry_run:
        print(message + "\nfiles:\n" + "\n".join(f"  {f}" for f in files))
        return 0
    solutions = [f for f in files if Path(f).match(SOLUTION_GLOB) and (root / f).exists()]
    if not args.skip_tests and not run_solutions(root, solutions):
        print("not committing; fix the failures or pass --skip-tests", file=sys.stderr)
        return 1

    # Commit only these paths, leaving anything else staged as it was.
    git(root, "add", "-A", "--", *files)
    git(root, "commit", "-s", "-F", "-", "--", *files, stdin=message)
    print(git(root, "log", "-1", "--format=%h %s").strip())
    return 0


if __name__ == "__main__":
    sys.exit(main())
