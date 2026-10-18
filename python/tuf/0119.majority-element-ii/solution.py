# 119. Majority Element-II
# https://takeuforward.org/practice/dsa/majority-element-ii

# @code=begin
class Solution:
    def majorityElementTwo(self, nums):
        x, y = None, None
        xc, yc = 0, 0

        for num in nums:
            if x == num:
                xc += 1
            elif y == num:
                yc += 1
            elif xc == 0:
                x, xc = num, 1
            elif yc == 0:
                y, yc = num, 1
            else:
                xc -= 1
                yc -= 1

        req = len(nums) // 3
        xc = nums.count(x)
        yc = nums.count(y)
        if xc > req and yc > req:
            return [x, y]
        if xc > req:
            return [x]
        if yc > req:
            return [y]
        return []
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
            got = json.dumps(Solution().majorityElementTwo(**json.loads(args)))
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
