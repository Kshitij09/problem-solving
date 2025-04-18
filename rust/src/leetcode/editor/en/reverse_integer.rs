struct Solution;
//leetcode submit region begin(Prohibit modification and deletion)
impl Solution {
    const OVERFLOW: i32 = i32::MAX / 10;
    pub fn reverse(x: i32) -> i32 {
        if x == i32::MIN { return 0 } // abs panics
        let is_negative = x < 0;
        let mut num = x.abs();
        let mut reverse = 0;

        while num > 0 {
            let digit = num % 10;
            num /= 10;
            if Self::is_overflow(reverse, digit) { return 0 }
            reverse = reverse * 10 + digit;
        }
        if is_negative { reverse * -1 } else { reverse }
    }

    fn is_overflow(current: i32, next_digit: i32) -> bool {
        current > Self::OVERFLOW || current == Self::OVERFLOW && next_digit > 7
    }
}
//leetcode submit region end(Prohibit modification and deletion)

#[cfg(test)]
mod tests {
    use super::Solution;
    macro_rules! reverse_test {
        ($name: ident, $input: expr, $expected: expr) => {
            #[test]
            fn $name() {
                assert_eq!(
                    Solution::reverse($input),
                    $expected
                )
            }
        };
    }

    reverse_test!(test_123, 123, 321);
    reverse_test!(test_neg_123, -123, -321);
    reverse_test!(test_120, 120, 21);
    reverse_test!(test_2147483647, 2147483647, 0);
    reverse_test!(test_neg_2147483648, -2147483648, 0);
    reverse_test!(test_1534236469, 1534236469, 0);
}