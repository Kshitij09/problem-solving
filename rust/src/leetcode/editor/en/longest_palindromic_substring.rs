struct Solution;
//leetcode submit region begin(Prohibit modification and deletion)
impl Solution {
    pub fn longest_palindrome(s: String) -> String {
        let mut max_palin = &s[0..0];
        let chars = s.as_bytes();
        // odd palindromes
        for src in 0..s.len() {
            let expansion = Self::expansion(&chars, src, src) - 1;
            let len = expansion * 2 + 1;
            if len > max_palin.len() {
                max_palin = &s[src-expansion..=src+expansion];
            }
        }
        // even length
        for right in 1..s.len() {
            let left = right-1;
            if chars[right] == chars[left] {
                let mut expansion = Self::expansion(&chars, left, right);
                let len = expansion * 2;
                expansion -= 1; // to ease further calculations
                if len > max_palin.len() {
                    max_palin = &s[left-expansion..=right+expansion];
                }
            }
        }
        max_palin.to_string()
    }

    fn expansion(input: &[u8], mut left: usize, mut right: usize) -> usize {
        let mut expansion = 0;
        while (left >= 0 && right < input.len() && input[left] == input[right]) {
            expansion += 1;
            if left == 0 { break }
            left -= 1;
            right += 1;
        }
        expansion
    }
}
//leetcode submit region end(Prohibit modification and deletion)

#[cfg(test)]
mod tests {
    use super::Solution;

    macro_rules! palindrome_test {
        ($input: ident, $expected: expr) => {
            #[test]
            fn $input() {
                assert_eq!(
                    Solution::longest_palindrome(stringify!($input).to_string()),
                    $expected.to_string()
                )
            }
        };
    }
    palindrome_test!(babad, "bab");
    palindrome_test!(cbbd, "bb");
    palindrome_test!(abcba, "abcba");
    palindrome_test!(abcdef, "a");
    palindrome_test!(a, "a");
}