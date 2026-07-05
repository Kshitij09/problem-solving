load("@rules_java//toolchains:remote_java_repository.bzl", "remote_java_repository")

def _azul_jdk_extension_impl(ctx):
    # macOS ARM64 (Apple Silicon)
    remote_java_repository(
        name = "zulu_jdk_25_macos_aarch64",
        urls = ["https://cdn.azul.com/zulu/bin/zulu25.34.17-ca-jdk25.0.3-macosx_aarch64.tar.gz"],
        sha256 = "74847e7ac930ad143950607421dd6c0a7ccb3d1cbbd8b1665f24ab62b8b7de42",
        strip_prefix = "zulu25.34.17-ca-jdk25.0.3-macosx_aarch64/Contents/Home",
        version = "25",
        target_compatible_with = [
            "@platforms//os:osx",
            "@platforms//cpu:aarch64",
        ],
    )

    # macOS x64 (Intel)
    remote_java_repository(
        name = "zulu_jdk_25_macos_x64",
        urls = ["https://cdn.azul.com/zulu/bin/zulu25.34.17-ca-jdk25.0.3-macosx_x64.tar.gz"],
        sha256 = "ecc005bf7b129c035379e0de9bebfd667be93098f5f1e329cd498c46769167ae",
        strip_prefix = "zulu25.34.17-ca-jdk25.0.3-macosx_x64/Contents/Home",
        version = "25",
        target_compatible_with = [
            "@platforms//os:osx",
            "@platforms//cpu:x86_64",
        ],
    )

    # Linux x86_64
    remote_java_repository(
        name = "zulu_jdk_25_linux_x64",
        urls = ["https://cdn.azul.com/zulu/bin/zulu25.34.17-ca-jdk25.0.3-linux_x64.tar.gz"],
        sha256 = "3a93235f4e5b313232647916219e91dc5cca0b9835c9b52a404ba58c43571697",
        strip_prefix = "zulu25.34.17-ca-jdk25.0.3-linux_x64",
        version = "25",
        target_compatible_with = [
            "@platforms//os:linux",
            "@platforms//cpu:x86_64",
        ],
    )

    # Linux ARM64
    remote_java_repository(
        name = "zulu_jdk_25_linux_aarch64",
        urls = ["https://cdn.azul.com/zulu/bin/zulu25.34.17-ca-jdk25.0.3-linux_aarch64.tar.gz"],
        sha256 = "2610e5c64df94cee5fb591d70183c5bf8532d8e1d394c754e8ba06751bcceb1b",
        strip_prefix = "zulu25.34.17-ca-jdk25.0.3-linux_aarch64",
        version = "25",
        target_compatible_with = [
            "@platforms//os:linux",
            "@platforms//cpu:aarch64",
        ],
    )

azul_jdk = module_extension(
    implementation = _azul_jdk_extension_impl,
)
