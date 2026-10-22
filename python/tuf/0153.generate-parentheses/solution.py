# 153. Generate Parentheses
# https://takeuforward.org/practice/dsa/generate-parentheses
from itertools import combinations


#
# 2
# ()() (())
# (
#  ((
#   (()
#    (())
#  ()
#
#
# @code=begin
def build(prefix, valid_strings, opened, closed, pairs):
    if len(prefix) == 2*pairs:
        valid_strings.append(''.join(prefix))
        return
    if opened < pairs:
        prefix.append('(')
        build(prefix, valid_strings, opened + 1, closed, pairs)
        del prefix[-1]
    if closed < opened:
        prefix.append(')')
        build(prefix, valid_strings, opened, closed + 1, pairs)
        del prefix[-1]

class Solution:
    def generateParenthesis(self, n):
        valid_strings = []
        build(prefix=[], valid_strings=valid_strings, opened=0, closed=0, pairs=n)
        return valid_strings
# @code=end


if __name__ == "__main__":
    # Runs the cases in testcases.txt. Not submitted.
    import json
    import re
    import sys
    import traceback
    from pathlib import Path

    # True when the problem accepts the answer in any order: lists are then
    # compared as multisets, at every level of nesting.
    ANY_ORDER = False

    def parse(value):
        try:
            return json.loads(value)
        except json.JSONDecodeError:
            return value

    def canonical(value):
        if ANY_ORDER and isinstance(value, list):
            return sorted((canonical(v) for v in value), key=json.dumps)
        return value

    def read_cases(path):
        for block in re.split(r"\n\s*\n", path.read_text().strip()):
            fields = {}
            for line in block.splitlines():
                key, _, value = line.partition(":")
                fields[key.strip()] = value.strip()
            yield fields["input"], fields.get("output", "")

    failed = 0
    for n, (args, want) in enumerate(read_cases(Path(__file__).with_name("testcases.txt")), 1):
        try:
            got = json.dumps(Solution().generateParenthesis(**json.loads(args)))
        except Exception:
            failed += 1
            print(f"case {n}: ERROR  input {args}")
            traceback.print_exc(file=sys.stdout)
            continue
        if not want:
            print(f"case {n}: ?      got {got} (no expected output)")
        elif canonical(parse(got)) == canonical(parse(want)):
            print(f"case {n}: PASS   {got}")
        else:
            failed += 1
            print(f"case {n}: FAIL   input {args}\n        got  {got}\n        want {want}")
    sys.exit(1 if failed else 0)
