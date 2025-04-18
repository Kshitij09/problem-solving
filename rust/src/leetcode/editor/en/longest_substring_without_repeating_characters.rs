
struct Solution;
//leetcode submit region begin(Prohibit modification and deletion)
use std::cmp::max;
use std::collections::HashMap;
impl Solution {
    pub fn length_of_longest_substring(s: String) -> i32 {
        let mut start: usize = 0;
        let mut max_len: usize = 0;
        let mut seen = HashMap::new();
        for (end, ch) in s.chars().enumerate() {
            if let Some(&last_idx) = seen.get(&ch) {
                start = max(start, last_idx + 1);
            }
            max_len = max(max_len, end-start+1);
            seen.insert(ch, end);
        }
        max_len as i32
    }
}
//leetcode submit region end(Prohibit modification and deletion)

#[cfg(test)]
mod tests {
    use super::Solution;
    #[test]
    fn test_length_of_longest_string() {
        assert_eq!(Solution::length_of_longest_substring("abba".to_string()), 2)
    }
}