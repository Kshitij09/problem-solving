# 166. Power Set
# https://takeuforward.org/practice/dsa/power-set

# 1 2 3
# f([], 0)
#   f([], 1)
#     f([],2)
#     f([2],2)
#   f([1], 1)

# @code=begin
def build(subset, index, power_set, nums):
    if index == len(nums):
        power_set.append(subset.copy())
        return
    subset.append(nums[index])
    build(subset, index + 1, power_set, nums)
    subset.pop()
    build(subset, index + 1, power_set, nums)

class Solution:
    def powerSet(self, nums):
        power_set = []
        build(subset=[],index=0,power_set=power_set,nums=nums)
        return power_set
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
            got = json.dumps(Solution().powerSet(**json.loads(args)))
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
