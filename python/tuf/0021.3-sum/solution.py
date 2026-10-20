# 21. 3 Sum
# https://takeuforward.org/practice/dsa/3-sum

# -1 -1 -1 2 3
# i 0
# j 2
# k 2
#
# @code=begin
class Solution:
    def threeSum(self, nums: list) -> list[list]:
        nums.sort()
        n = len(nums)
        target = 0
        answers = []

        if n < 3:
            return answers

        for i in range(n):
            if 0 < i < n-2 and nums[i] == nums[i-1]:
                continue

            j = i+1
            k = n-1
            while j < k:
                sum = nums[i] + nums[j] + nums[k]
                if sum == target:
                    answers.append([nums[i], nums[j], nums[k]])
                    j += 1
                    k -= 1
                    while j < k and nums[j] == nums[j-1]:
                        j += 1
                    while j < k and nums[k] == nums[k+1]:
                        k -= 1
                elif sum < target:
                    j += 1
                else:
                    k -= 1

        return answers
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
    ANY_ORDER = True

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
            got = json.dumps(Solution().threeSum(**json.loads(args)))
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
