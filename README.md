# problem-solving

Collection of leetcode & codeforces problems solved using Java 8, Java 17, Kotlin
and Python, plus small Python projects (`projects-python/`)

JVM solutions use the [leetcode-editor](https://github.com/shuzijun/leetcode-editor)
IntelliJ plugin. Python solutions use the [leetgo](https://github.com/j178/leetgo) CLI
(see [LeetCode with leetgo](#leetcode-with-leetgo)).

## Bazel Build Support

Every language builds with Bazel (bzlmod). Bazel's version is pinned in `.bazelversion`;
use [bazelisk](https://github.com/bazelbuild/bazelisk) so it's picked up automatically.

```bash
bazel test //...
```

### Running a Solution
You can build and run any solution target directly using:
```bash
bazel run //<module-directory>:<ProblemName>
```
For example:
```bash
bazel run //leetcode-kotlin:TwoSum
```

### Adding a New Solution
When you add a new problem solution file under a JVM module, update the `main_srcs` parameter inside the module's `BUILD.bazel` file:
```bazel
kotlin_problem_targets(
    name = "leetcode_kotlin",
    main_srcs = [
        ...
        "src/main/kotlin/com/kshitijpatil/leetcode/editor/en/NewProblem.kt",
    ],
)
```

### Adding Support Code / Shared Utils
If you add helper or support classes (like custom data structures, Fast I/O helpers) that are imported by other solution files:
1. List them under the `support_srcs` parameter of the macro.
2. The macro will compile them into a shared library that all `main_srcs` targets depend on automatically.

For example:
```bazel
java_problem_targets(
    name = "codeforces_java8",
    support_srcs = [
        "src/main/java/IO.java",
        "src/main/java/IntMath.java",
    ],
    main_srcs = [
        ...
    ],
)
```

## Python

Python 3.14, in both Bazel's hermetic toolchain and the local venv. Dependencies (just
`pytest`) are pinned in `bazel/python/requirements_lock.txt`.

### Local setup (for IntelliJ and quick iteration)
```bash
python3.14 -m venv .venv
.venv/bin/pip install --require-hashes -r bazel/python/requirements_lock.txt
.venv/bin/python -m pytest
```
Set `.venv` as the IntelliJ project interpreter and run tests with the pytest runner.

### Layout and targets
Each top-level directory under a Python module is one problem or project. The
`py_problem_targets` macro (`bazel/python/problems.bzl`) globs them, so **no BUILD
edits are needed** when adding files. For a directory `d` it generates:
- `d/<module>`: a `py_binary` per source, for `if __name__ == "__main__":` try-outs
- `d/<test_module>`: a `py_test` per `test_*.py`, run under pytest

```bash
bazel test //projects-python/...
bazel run //projects-python:00_template.greet
```
Modules within a directory import each other flatly (`from greet import greet`). Keep
test file names unique across directories (`test_<project>.py`).

### Updating dependencies
Edit `bazel/python/requirements.in`, then:
```bash
bazel run //bazel/python:requirements.update
```

## LeetCode with leetgo

Install: `go install github.com/j178/leetgo@v1.5.0` (and put `$(go env GOPATH)/bin` on `PATH`).
Log in to leetcode.com in your browser; leetgo reads the session cookies from it
(`leetgo.yaml` → `leetcode.credentials`).

```bash
leetgo pick 150            # leetcode-python/0150.evaluate-reverse-polish-notation/
                           #   solution.py, testcases.txt (examples), question.md
.venv/bin/python -m pytest leetcode-python/0150.evaluate-reverse-polish-notation
leetgo submit last
```

- Write the solution between `# @lc code=begin` and `# @lc code=end`. **Only that part is
  submitted**; the `if __name__ == "__main__":` harness below it is for local runs.
- `testcases.txt` holds the examples. Add your own cases in the same `input:` / `output:` format.
  A case without an output is skipped locally.
- Each example becomes a pytest item (`case1`, `case2`, ...) via `bazel/python/pytest_leetgo.py`,
  so they show up individually in IntelliJ's test runner and in `bazel test //leetcode-python/...`.
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
- `leetgo test last -L` also works; it uses its own venv in `leetcode-python/.venv` and
  doesn't know about `judge.py`.

