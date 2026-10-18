# 1020. Merge Sorting
# https://takeuforward.org/practice/dsa/merge-sorting

# @code=begin
class Solution:
    def merge(self, nums, start, mid, end):
        merged = []
        i, j = start, mid
        while i < mid and j < end:
            if nums[i] <= nums[j]:
                merged.append(nums[i])
                i += 1
            else:
                merged.append(nums[j])
                j += 1
        merged.extend(nums[i:mid])
        merged.extend(nums[j:end])
        nums[start:end] = merged

    def merge_sort(self, nums, start, end):
        if end - start <= 1:
            return
        mid = start + (end - start) // 2
        self.merge_sort(nums, start, mid)
        self.merge_sort(nums, mid, end)
        self.merge(nums, start, mid, end)
    def mergeSort(self, nums):
        self.merge_sort(nums, 0, len(nums))
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
            got = json.dumps(Solution().mergeSort(**json.loads(args)))
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
