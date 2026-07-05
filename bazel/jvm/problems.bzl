load("@rules_kotlin//kotlin:jvm.bzl", "kt_jvm_binary", "kt_jvm_library")
load("@rules_java//java:defs.bzl", "java_binary", "java_library")

def _remove_prefix(s, prefix):
    if s.startswith(prefix):
        return s[len(prefix):]
    return s

def _remove_suffix(s, suffix):
    if s.endswith(suffix):
        return s[:-len(suffix)]
    return s

def _java_main_class(src):
    rel_path = _remove_prefix(src, "src/main/java/")
    rel_path = _remove_suffix(rel_path, ".java")
    return rel_path.replace("/", ".")

def _java_binary_name(src):
    filename = src.split("/")[-1]
    return _remove_suffix(filename, ".java")

def _kotlin_main_class(src):
    rel_path = _remove_prefix(src, "src/main/kotlin/")
    rel_path = _remove_suffix(rel_path, ".kt")
    return rel_path.replace("/", ".") + "Kt"

def _kotlin_binary_name(src):
    filename = src.split("/")[-1]
    return _remove_suffix(filename, ".kt")

_java_config = struct(
    library = java_library,
    binary = java_binary,
    main_class = _java_main_class,
    binary_name = _java_binary_name,
)

_kotlin_config = struct(
    library = kt_jvm_library,
    binary = kt_jvm_binary,
    main_class = _kotlin_main_class,
    binary_name = _kotlin_binary_name,
)

def _jvm_problem_targets(name, main_srcs, support_srcs, config, extra_args = {}):
    if support_srcs:
        config.library(
            name = name,
            srcs = support_srcs,
            visibility = ["//visibility:public"],
            **extra_args
        )

    for src in main_srcs:
        deps = []
        if support_srcs:
            deps.append(":" + name)

        config.binary(
            name = config.binary_name(src),
            srcs = [src],
            deps = deps,
            main_class = config.main_class(src),
            visibility = ["//visibility:public"],
            **extra_args
        )

def java_problem_targets(name, main_srcs, support_srcs = [], javacopts = []):
    _jvm_problem_targets(
        name = name,
        main_srcs = main_srcs,
        support_srcs = support_srcs,
        config = _java_config,
        extra_args = {"javacopts": javacopts},
    )

def kotlin_problem_targets(name, main_srcs, support_srcs = []):
    _jvm_problem_targets(
        name = name,
        main_srcs = main_srcs,
        support_srcs = support_srcs,
        config = _kotlin_config,
    )
