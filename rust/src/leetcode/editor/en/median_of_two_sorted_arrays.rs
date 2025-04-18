struct Solution;
//leetcode submit region begin(Prohibit modification and deletion)
use std::cmp::{max, min};
impl Solution {
    pub fn find_median_sorted_arrays(small: Vec<i32>, big: Vec<i32>) -> f64 {
        let (len1, len2) = (small.len(), big.len());
        if len1 > len2 {
            return Self::find_median_sorted_arrays(big, small);
        }
        let total_elements = len1 + len2;
        let half_length = (total_elements+1) / 2;
        let mut low= 0;
        let mut high = len1;
        while (low <= high) {
            let mid1 = low + (high-low)/2;
            let mid2 = half_length - mid1;
            let l1 = if mid1 > 0 { small[mid1 - 1] } else { i32::MIN };
            let r1 = if mid1 < len1 { small[mid1] } else { i32::MAX };
            let l2 = if mid2 > 0 { big[mid2 - 1] } else { i32::MIN };
            let r2 = if mid2 < len2 { big[mid2] } else { i32::MAX };
            if l1 <= r2 && l2 <= r1 {
                return if (total_elements & 1) == 1 {
                    max(l1, l2) as f64
                } else {
                    (max(l1, l2) + min(r1, r2)) as f64 / 2.0
                }
            }
            if l1 > r2 {
                high = mid1 - 1;
            } else {
                low = mid1 + 1;
            }
        }
        0.0
    }
}
//leetcode submit region end(Prohibit modification and deletion)
