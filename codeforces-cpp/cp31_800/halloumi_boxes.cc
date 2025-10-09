#include <ios>
#include <iostream>
using namespace std;

int main() {
  // optimize io
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);
  cout.tie(nullptr);

  int t;
  cin >> t;
  while (t--) {
    int n, k, prev=-1, cur;
    cin >> n >> k;
    bool is_non_decreasing = true;
    for (int i = 0; i < n; i++) {
      cin >> cur;
      if (cur < prev) {
        is_non_decreasing = false;
      }
      prev = cur;
    }
    if (n <= 1 || k > 1 || is_non_decreasing) {
      cout << "YES";
    } else {
      cout << "NO";
    }
    cout << endl;
  }
  return 0;
}