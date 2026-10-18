# 138. Find the repeating and missing number
# https://takeuforward.org/practice/dsa/find-the-repeating-and-missing-number

# @code=begin
class Solution:
    def findMissingRepeatingNumbers(self, nums):
        n = len(nums)
        sum_n = sum(range(1, n+1))
        sum_nums = sum(nums)
        # assume x is missing and y is duplicate
        x_minus_y = sum_n - sum_nums

        sum_n_sqr = sum(i*i for i in range(1, n+1))
        sum_nums_sqr = sum(i*i for i in nums)
        xsqr_minus_ysqr = sum_n_sqr - sum_nums_sqr
        x_plus_y = xsqr_minus_ysqr // x_minus_y

        x = (x_plus_y + x_minus_y) // 2
        y = x_plus_y - x
        # expected order (duplicate, missing)
        return y, x
# @code=end


if __name__ == "__main__":
    # Runs the cases in testcases.txt. Not submitted.
    import json
    import re
    import sys
    import traceback
    from pathlib import Path

    def parse(value):
        try:
            return json.loads(value)
        except json.JSONDecodeError:
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
            got = json.dumps(Solution().findMissingRepeatingNumbers(**json.loads(args)))
        except Exception:
            failed += 1
            print(f"case {n}: ERROR  input {args}")
            traceback.print_exc(file=sys.stdout)
            continue
        if not want:
            print(f"case {n}: ?      got {got} (no expected output)")
        elif parse(got) == parse(want):
            print(f"case {n}: PASS   {got}")
        else:
            failed += 1
            print(f"case {n}: FAIL   input {args}\n        got  {got}\n        want {want}")
    sys.exit(1 if failed else 0)
