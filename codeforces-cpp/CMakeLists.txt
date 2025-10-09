cmake_minimum_required(VERSION 3.20)
project(codeforces_cpp)

# Set C++23 standard
set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

# Try to find LLVM installation (Homebrew or system)
# Uncomment and adjust if you have LLVM installed via Homebrew:
 set(CMAKE_CXX_COMPILER /opt/homebrew/opt/llvm/bin/clang++)
# Or for older Intel Macs: /usr/local/opt/llvm/bin/clang++

# Use libc++ (LLVM's C++ standard library)
add_compile_options(-stdlib=libc++)
add_link_options(-stdlib=libc++)

# Add subdirectories
add_subdirectory(cp31_800)