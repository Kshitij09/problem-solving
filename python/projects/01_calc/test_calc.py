import pytest

from calc import evaluate


@pytest.mark.parametrize(
    "src, expected",
    [
        ("42", 42),
        ("1 + 2", 3),
        ("2 + 3 * 4", 14),            # precedence
        ("10 - 4 - 3", 3),            # left-associative minus
        ("100 / 10 / 5", 2),          # left-associative divide
        ("2 * (3 + 4) - 5", 9),
        ("((2))", 2),
        ("(1 + 2) * (3 + 4)", 21),
        ("7 / 2", 3),                 # integer division
        ("  3+  5 / 2 ", 5),          # whitespace anywhere
        ("2 * (3 + (4 - 1)) * 2", 24),
    ],
)
def test_evaluate(src, expected):
    assert evaluate(src) == expected


@pytest.mark.parametrize("src", ["1 +", "(1 + 2", "1 + 2)", "", "1 $ 2"])
def test_evaluate_rejects_bad_input(src):
    with pytest.raises(ValueError):
        evaluate(src)
