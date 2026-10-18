import subprocess
from pathlib import Path

import pytest

from commit_solution import Problem, alternative_name, commit_message, main, parse_tuf

PROBLEM = Problem("takeUforward", "138", "Find the repeating and missing number",
                  "https://takeuforward.org/practice/dsa/find-the-repeating-and-missing-number")
BODY = f"{PROBLEM.name}\n{PROBLEM.url}"


def tuf_solution(body: str = "sys.exit(0)") -> str:
    return (f"# 138. {PROBLEM.name}\n# {PROBLEM.url}\n\n"
            f"# @code=begin\n# @code=end\n\nimport sys\n{body}\n")


def test_alternative_name():
    assert alternative_name("python/tuf/0138.x/solution_xor.py") == "xor"
    assert alternative_name("solution.py") is None
    assert alternative_name("testcases.txt") is None


def test_parse_tuf(tmp_path):
    (tmp_path / "solution.py").write_text(tuf_solution())
    assert parse_tuf(tmp_path) == PROBLEM


def test_parse_tuf_without_header(tmp_path):
    (tmp_path / "solution.py").write_text("class Solution: pass\n")
    with pytest.raises(ValueError):
        parse_tuf(tmp_path)


@pytest.mark.parametrize(
    ("is_new", "added", "title"),
    [
        (True, ["solution.py", "testcases.txt"], "Solve takeUforward 138"),
        (False, ["solution_xor.py"], "Add takeUforward 138 alternative: xor"),
        (False, ["solution_xor.py", "solution_math.py"], "Add takeUforward 138 alternatives: math, xor"),
        (False, [], "Update takeUforward 138"),
    ],
)
def test_commit_message(is_new, added, title):
    assert commit_message(PROBLEM, is_new, added) == f"{title}\n\n{BODY}\n"


def test_commit_message_with_note():
    msg = commit_message(PROBLEM, False, [], note="O(1) space with XOR\n")
    assert msg == f"Update takeUforward 138\n\n{BODY}\n\nO(1) space with XOR\n"


# --- Against a real (temporary) git repository ---------------------------------------------


def git(repo: Path, *args: str) -> str:
    return subprocess.run(["git", *args], cwd=repo, check=True, capture_output=True, text=True).stdout


@pytest.fixture
def repo(tmp_path, monkeypatch):
    git(tmp_path, "init", "-q")
    git(tmp_path, "config", "user.name", "Test")
    git(tmp_path, "config", "user.email", "test@example.com")
    (tmp_path / "README.md").write_text("repo\n")
    git(tmp_path, "add", ".")
    git(tmp_path, "commit", "-qm", "init")
    monkeypatch.chdir(tmp_path)
    return tmp_path


def last_message(repo: Path) -> str:
    return git(repo, "log", "-1", "--format=%B")


def test_solve_then_alternative_then_update(repo):
    problem = repo / "python/tuf/0138.find-the-repeating-and-missing-number"
    problem.mkdir(parents=True)
    (problem / "solution.py").write_text(tuf_solution())
    (problem / "testcases.txt").write_text("input: {}\noutput:\n")

    assert main([str(problem)]) == 0
    assert last_message(repo).startswith(f"Solve takeUforward 138\n\n{BODY}\n\nSigned-off-by: Test")

    (problem / "solution_xor.py").write_text(tuf_solution())
    (repo / "README.md").write_text("unrelated, stays uncommitted\n")
    assert main([str(problem / "solution_xor.py")]) == 0
    assert last_message(repo).startswith("Add takeUforward 138 alternative: xor\n")
    assert git(repo, "status", "--porcelain").strip() == "M README.md"

    (problem / "solution.py").write_text(tuf_solution("sys.exit(0)  # faster"))
    assert main([str(problem), "--note", "Single pass"]) == 0
    assert last_message(repo).startswith(f"Update takeUforward 138\n\n{BODY}\n\nSingle pass\n")


def test_failing_solution_is_not_committed(repo, capsys):
    problem = repo / "python/tuf/0001.x"
    problem.mkdir(parents=True)
    (problem / "solution.py").write_text(tuf_solution("sys.exit(1)"))

    assert main([str(problem)]) == 1
    assert "not committing" in capsys.readouterr().err
    assert last_message(repo).strip() == "init"
    assert main([str(problem), "--skip-tests"]) == 0


def test_unknown_site(repo):
    elsewhere = repo / "notes/0001.x"
    elsewhere.mkdir(parents=True)
    (elsewhere / "solution.py").write_text(tuf_solution())
    with pytest.raises(SystemExit, match="isn't a problem directory"):
        main([str(elsewhere)])


def test_no_arguments_prints_help(capsys):
    assert main([]) == 2
    assert "Solve takeUforward 138" in capsys.readouterr().out
