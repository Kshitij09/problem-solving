cmake_minimum_required(VERSION 3.20)

# Set compiler BEFORE project() command
set(CMAKE_CXX_COMPILER /opt/homebrew/bin/g++-15)

project(codeforces_cpp)

# Set C++23 standard
set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

# Use libc++ (LLVM's C++ standard library)
#add_compile_options(-stdlib=libc++)
#add_link_options(-stdlib=libc++)

# Add subdirectories
add_subdirectory(cp31_800)