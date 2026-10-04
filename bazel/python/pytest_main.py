"""Entry point that lets a py_test run its test file under pytest."""

import sys

import pytest

if __name__ == "__main__":
    sys.exit(pytest.main(["-q", "-p", "no:cacheprovider", *sys.argv[1:]]))
