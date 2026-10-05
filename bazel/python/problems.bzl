load("@rules_python//python:defs.bzl", "py_binary", "py_library", "py_test")

_PYTEST = "@pypi//pytest"
_PYTEST_MAIN = "//bazel/python:pytest_main.py"
_PYTEST_LEETGO = "//bazel/python:pytest_leetgo"

def _basename(path):
    return path.split("/")[-1].removesuffix(".py")

def py_problem_targets(name = "problems", deps = []):
    """Generates targets for every problem directory under the current package.

    Each top-level subdirectory is one problem (or project). For a directory `d`:
      - `d.lib`: py_library of its non-test sources, with `d` on the import path
        so sources import each other flatly (`import calc`).
      - `d.<module>`: py_binary per non-test source, for `if __name__ == "__main__"` try-outs.
      - `d.<test_module>`: py_test per `test_*.py`, run under pytest.
      - `d.testcases`: py_test for a leetgo `testcases.txt` (one pytest item per example,
        plus a `submission` check); see //bazel/python:pytest_leetgo.py.

    Target names are flat (`d.x`, not `d/x`): rules_python names an executable's venv after
    the last path segment of its name, so `a/solution` and `b/solution` would collide.

    New directories and files are picked up by glob; no BUILD edits needed.
    """
    srcs = native.glob(["*/**/*.py"], exclude = [".*/**"], allow_empty = True)
    dirs = sorted({src.split("/")[0]: None for src in srcs}.keys())

    tests = []
    for d in dirs:
        test_srcs = native.glob([d + "/**/test_*.py"], allow_empty = True)
        lib_srcs = native.glob([d + "/**/*.py"], exclude = test_srcs, allow_empty = True)
        data = native.glob([d + "/**/*"], exclude = [d + "/**/*.py"], allow_empty = True)

        py_library(
            name = d + ".lib",
            srcs = lib_srcs,
            data = data,
            imports = [d],
            deps = deps,
        )

        for src in lib_srcs:
            py_binary(
                name = d + "." + _basename(src),
                srcs = [src],
                main = src,
                deps = [":" + d + ".lib"],
            )

        for src in test_srcs:
            test_name = d + "." + _basename(src)
            py_test(
                name = test_name,
                srcs = [_PYTEST_MAIN, src],
                main = _PYTEST_MAIN,
                args = ["$(rootpath %s)" % src],
                deps = [":" + d + ".lib", _PYTEST],
            )
            tests.append(":" + test_name)

        testcases = native.glob([d + "/testcases.txt"], allow_empty = True)
        if testcases:
            test_name = d + ".testcases"
            py_test(
                name = test_name,
                srcs = [_PYTEST_MAIN],
                main = _PYTEST_MAIN,
                args = ["-p", "pytest_leetgo", "$(rootpath %s)" % testcases[0]],
                data = testcases,
                deps = [":" + d + ".lib", _PYTEST_LEETGO, _PYTEST],
            )
            tests.append(":" + test_name)

    native.test_suite(
        name = name,
        tests = tests,
    )
