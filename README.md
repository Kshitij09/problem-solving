# problem-solving

Collection of leetcode & codeforces problems solved using Java 8, Java 17, Kotlin
and Python, plus small Python projects (`python/projects/`)

JVM solutions use the [leetcode-editor](https://github.com/shuzijun/leetcode-editor)
IntelliJ plugin. Python solutions use the [leetgo](https://github.com/j178/leetgo) CLI
(see [LeetCode with leetgo](#leetcode-with-leetgo)).

## IDE setup

Open the repo root in IntelliJ IDEA. It's a plain IntelliJ (JPS) project: the modules are listed
in `.idea/modules.xml`, with one `.iml` per language module. There's no Gradle or Bazel.

| Module | Language level | Notes |
|---|---|---|
| `leetcode-java8`, `codeforces-java8` | Java 8 | JUnit 5 for `codeforces-java8` tests |
| `leetcode-java17`, `codeforces-java17` | Java 17 | |
| `leetcode-kotlin` | Kotlin (JVM 17) | `KotlinJavaRuntime` library |
| `python` (`python/python.iml`) | Python 3.14 | everything under `python/` |

The project JDK is `zulu-17`. The JUnit and Kotlin libraries are Maven-backed
(`.idea/libraries/`), and IntelliJ downloads them on first open.

To run a solution, use the gutter arrow on `main`. New files need no build edits.

## Python

All Python lives in `python/`, a [uv](https://docs.astral.sh/uv/) project on Python 3.14.
`python/pyproject.toml` holds the dev
dependencies (pytest, plus `leetgo_py` and `sortedcontainers` for leetgo solutions) and the
pytest config. `uv.lock` pins them. Run uv commands from `python/`:

```bash
cd python
uv sync          # create/refresh python/.venv
uv run pytest    # run every Python test
uv run python projects/01_calc/calc.py
```

In IntelliJ, set the `python` module's interpreter to the uv `python/.venv`: Settings → Project
Structure → Modules → python, or Add Interpreter → uv → existing environment. Then a right-click
→ Run on any `.py` file gives you a normal interactive process, and pytest is the test runner.

### Layout
```
python/
  pyproject.toml  uv.lock  .python-version  python.iml  leetgo.yaml
  tools/       pytest_leetgo plugin
  projects/    the project track (copy 00_template/)
  leetcode/    leetgo problems
```

Each top-level directory under `projects/` or `leetcode/` is one problem or
project. Modules in a directory import each other flatly (`from greet import greet`), because
both pytest and the script runner put the file's own directory on `sys.path`. Keep test file
names unique across directories (`test_<project>.py`).

### Adding dependencies
```bash
uv add --dev <package>
```

## LeetCode with leetgo

Install: `go install github.com/j178/leetgo@v1.5.0` (and put `$(go env GOPATH)/bin` on `PATH`).
Log in to leetcode.com in your browser; leetgo reads the session cookies from it
(`python/leetgo.yaml` → `leetcode.credentials`).

```bash
cd python
leetgo pick 150            # leetcode/0150.evaluate-reverse-polish-notation/
                           #   solution.py, testcases.txt (examples), question.md
uv run pytest leetcode/0150.evaluate-reverse-polish-notation
leetgo submit last
```

- Write the solution between `# @lc code=begin` and `# @lc code=end`. **Only that part is
  submitted**; the `if __name__ == "__main__":` harness below it is for local runs.
- `testcases.txt` holds the examples. Add your own cases in the same `input:` / `output:` format.
  A case without an output is skipped locally.
- Each example becomes a pytest item (`case1`, `case2`, ...) via `python/tools/pytest_leetgo.py`,
  so they show up individually in IntelliJ's test runner.
- The extra `submission` item reruns all examples using only the code inside the markers, plus
  `from typing import *`, `ListNode` and `TreeNode`. It fails if you rely on imports or helpers
  defined outside the markers. Put imports (`collections`, `heapq`, ...) inside the markers.
- Outputs are compared as JSON (floats within 1e-5). Top-level lists ignore order when the
  question says "in any order". For anything else (nested lists in any order, multiple valid
  answers), add a `judge.py` next to `solution.py`:
  ```python
  def judge(expected, actual) -> bool:
      return sorted(map(sorted, expected)) == sorted(map(sorted, actual))
  ```
- `leetgo test last -L` also works; it uses its own venv in `python/leetcode/.venv` and
  doesn't know about `judge.py`.

