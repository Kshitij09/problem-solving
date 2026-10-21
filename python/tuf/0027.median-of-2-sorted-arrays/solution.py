# 27. Median of 2 sorted arrays
# https://takeuforward.org/practice/dsa/median-of-2-sorted-arrays

#
# 1 3 5 8 11 17 27
# 1 5 11 27
#
# 3 8        17
# 7+1 // 2 = 4
# 8+1 // 2 = 4
#
# @code=begin
class Solution:
    def median(self, nums_a, nums_b):
        len_a, len_b = len(nums_a), len(nums_b)
        if len_a > len_b:
            return self.median(nums_b, nums_a)

        total = len_a + len_b
        left_size = (total + 1) // 2 # elements in the left half
        low = max(0, left_size - len_b)
        high = min(left_size, len_a)

        while low <= high:
            cut_a = low + (high - low) // 2
            cut_b = left_size - cut_a

            right_a = nums_a[cut_a] if cut_a < len_a else float("inf")
            right_b = nums_b[cut_b] if cut_b < len_b else float("inf")
            left_a = nums_a[cut_a - 1] if cut_a >= 1 else float("-inf")
            left_b = nums_b[cut_b - 1] if cut_b >= 1 else float("-inf")

            if left_a <= right_b and left_b <= right_a:
                left_max = max(left_a, left_b)
                right_min = min(right_a ,right_b)
                if total % 2 == 0:
                    return (left_max + right_min) / 2
                else:
                    return left_max
            elif left_a > right_b:
                high = cut_a - 1
            else:
                low = cut_a + 1
        raise ValueError("Inputs must be sorted")
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
            got = json.dumps(Solution().median(**json.loads(args)))
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
