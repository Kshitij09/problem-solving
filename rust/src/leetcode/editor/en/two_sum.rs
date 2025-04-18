struct Solution;
//leetcode submit region begin(Prohibit modification and deletion)
use std::collections::HashMap;
impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut seen = HashMap::new();

        for (i, &num) in nums.iter().enumerate() {
            let complement = target - num;

            if let Some(&index) = seen.get(&complement) {
                return vec![index, i as i32];
            }
            seen.insert(num, i as i32);
        }
        vec![]
    }
}
//leetcode submit region end(Prohibit modification and deletion)
