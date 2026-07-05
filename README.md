# problem-solving

Collection of leetcode & codeforces problems solved using java-8, java-17 & rust

The project uses [leetcode-editor](https://github.com/shuzijun/leetcode-editor)
plugin for IntelliJ which fetches the problem locally and has ability to run &
even submit the solution from the IDE itself

## Bazel Build Support

This project supports building and running Java and Kotlin solutions using Bazel.

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

