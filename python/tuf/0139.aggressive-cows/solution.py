# 139. Aggressive Cows
# https://takeuforward.org/practice/dsa/aggressive-cows

# k=4
# x - - x x - - x - x x
# o     o       o     o
#
#
#
#
#
# @code=begin
from itertools import islice

def assign_cows(stalls, min_distance):
    if len(stalls) <= 1:
        return len(stalls)
    last = stalls[0]
    assigned = 1
    for curr in islice(stalls, 1, None):
        if curr - last >= min_distance:
            assigned += 1
            last = curr
    return assigned

class Solution:
    def aggressiveCows(self, nums, k):
        n = len(nums)
        assert n >= 2, f"expected n>=2, got {n}"
        stalls = sorted(nums)
        low, high = 1, stalls[n-1] - stalls[0]
        ans = 0
        while low <= high:
            dist = low + (high-low) // 2
            assigned = assign_cows(stalls, dist)
            if assigned >= k:
                ans = dist
                low = dist+1
            else:
                high = dist-1
        return ans
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
            got = json.dumps(Solution().aggressiveCows(**json.loads(args)))
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
