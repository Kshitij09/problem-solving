# 112. Search in rotated sorted array-II
# https://takeuforward.org/practice/dsa/search-in-rotated-sorted-array-2

#
# 4 4 4 4 5 5 2 3 4
#         x
# @code=begin
class Solution:
    def searchInARotatedSortedArrayII(self, nums, k):
        low, high = 0, len(nums) - 1
        while low <= high:
            mid = low + (high-low) // 2
            if nums[mid] == k:
                return True
            if nums[mid] == nums[low] and nums[mid] == nums[high]:
                low += 1
                high -= 1
            elif nums[mid] < nums[high]:
                if nums[mid] < k <= nums[high]:
                    low = mid + 1
                else:
                    high = mid - 1
            else:
                if nums[low] <= k < nums[mid]:
                    high = mid - 1
                else:
                    low = mid + 1
        return False
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
            got = json.dumps(Solution().searchInARotatedSortedArrayII(**json.loads(args)))
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
