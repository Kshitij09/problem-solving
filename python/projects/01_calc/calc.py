"""P01 calc: an expression evaluator REPL. See notes/projects.md#p01."""


def evaluate(src: str) -> int:
    """Evaluate an integer expression with + - * /, precedence and parentheses.

    `/` truncates toward zero (like LeetCode 227), so 7 / -2 == -3.
    Raises ValueError on malformed input. How you get there is up to you.
    """
    raise NotImplementedError


def repl() -> None:
    while True:
        try:
            line = input("calc> ").strip()
        except (EOFError, KeyboardInterrupt):
            print()
            return
        if not line:
            continue
        try:
            print(evaluate(line))
        except Exception as e:  # noqa: BLE001 - a REPL should survive bad input
            print(f"error: {e}")


if __name__ == "__main__":
    repl()
