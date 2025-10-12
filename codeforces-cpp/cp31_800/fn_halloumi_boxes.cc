//
// Created by Kshitij Patil on 11/10/25.
//

#include <ios>
#include <iostream>
#include <vector>
#include <ranges>
#include <algorithm>

using std::vector;
using std::cin;
using std::cout;

namespace views = std::views;
namespace ranges = std::ranges;

struct TestCase {
  int n;
  int k;
  vector<int> nums;
};

vector<TestCase> parse_test_cases(int t) {
  vector<TestCase> test_cases(t);
  for (auto& tc : test_cases) {
    cin >> tc.n >> tc.k;
    tc.nums.resize(tc.n);
    for (int i = 0; i < tc.n; i++) {
      cin >> tc.nums[i];
    }
  }
  return test_cases;
}

bool is_non_decreasing(const vector<int>& nums) {
  return ranges::all_of(
    nums | views::pairwise,
    [](const auto& pair) {
      auto [a, b] = pair;
      return a <= b;
    }
  );
}

bool solve_test_case(const TestCase& tc) {
  return tc.n <= 1 || tc.k > 1 || is_non_decreasing(tc.nums);
}

std::string_view to_answer(bool predicate) {
  return predicate ? "YES" : "NO";
}

int main() {
  std::ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  int t;
  cin >> t;

  vector<TestCase> test_cases = parse_test_cases(t);

  const auto results = test_cases
    | views::transform(solve_test_case)
    | views::transform(to_answer);

  for (const auto& result : results) {
    cout << result << '\n';
  }

  return 0;
}