# problem-solving

Collection of leetcode & codeforces problems solved using Java 8, Java 17, Kotlin
and Python, plus small Python projects (`projects-python/`)

The project uses [leetcode-editor](https://github.com/shuzijun/leetcode-editor)
plugin for IntelliJ which fetches the problem locally and has ability to run &
even submit the solution from the IDE itself

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
bazel run //projects-python:00_template/greet
```
Modules within a directory import each other flatly (`from greet import greet`). Keep
test file names unique across directories (`test_<project>.py`).

### Updating dependencies
Edit `bazel/python/requirements.in`, then:
```bash
bazel run //bazel/python:requirements.update
```

