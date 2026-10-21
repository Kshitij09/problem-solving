# 36. Sort an array of 0's 1's and 2's
# https://takeuforward.org/practice/dsa/sort-an-array-of-0's-1's-and-2's

# 0 0 0 0 1 1 1 0 2 1 2 2
#         i
#             j
#                   k
# upto i - all zeros
# i+1 to j - ones
# j+1 to k-1 - unsorted
# k to end - twos
# i - 4
# j - 6
# k - n
# @code=begin
class Solution:
    def sortZeroOneTwo(self, nums):
        n = len(nums)
        i = 0   # next zero, first one
        j = 0   # next one, j-1 is last one.
        k = n-1 # next two, currently unknown
        # j to k are unsorted
        while j <= k:
            match nums[j]:
                case 0:
                    nums[i], nums[j] =  nums[j], nums[i]
                    i += 1
                    j += 1
                case 1:
                    j += 1
                case 2:
                    nums[k], nums[j] = nums[j], nums[k]
                    k -= 1
                case _:
                    raise ValueError(f"expected 0,1 or 2, got {nums[j]}")
        return nums
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
            got = json.dumps(Solution().sortZeroOneTwo(**json.loads(args)))
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
