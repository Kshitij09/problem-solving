struct Solution;
//leetcode submit region begin(Prohibit modification and deletion)
impl Solution {
    const OVERFLOW: u32 = (i32::MAX / 10) as u32;
    pub fn my_atoi(s: String) -> i32 {
        if s.is_empty() { return 0 }
        let mut result = 0;
        let mut chars = s.chars();
        let mut sign = Sign::Positive;
        while let Some(c) = chars.next() {
            match c {
                '+' | '-' => {
                    sign = Sign::from(c).unwrap_or_default();
                    break
                }
                '0'..='9' => {
                    result = c.to_digit(10).unwrap();
                    break
                }
                ' ' => continue,
                _ => return 0
            }
        }
        for c in chars {
            match c.to_digit(10) {
                Some(next_digit) => {
                    if Self::is_overflow(result, next_digit, sign) {
                        return sign.limit()
                    }
                    result = result * 10 + next_digit
                }
                None => break
            }
        }
        (sign as i32) * (result as i32)
    }

    fn is_overflow(current: u32, next_digit: u32, sign: Sign) -> bool {
        if current > Self::OVERFLOW { return true }
        if current == Self::OVERFLOW {
            return next_digit > sign.max_last_digit() as u32
        }
        false
    }
}

#[derive(Copy, Clone)]
enum Sign {
    Positive = 1,
    Negative = -1
}

impl Default for Sign {
    fn default() -> Self {
        Sign::Positive
    }
}

impl Sign {
    fn from(c: char) -> Option<Self> {
        match c {
            '+' => Some(Sign::Positive),
            '-' => Some(Sign::Negative),
            _ => None
        }
    }

    fn limit(self: Self) -> i32 {
        match self {
            Sign::Positive => i32::MAX,
            Sign::Negative => i32::MIN
        }
    }

    fn max_last_digit(self: Self) -> u8 {
        match self {
            Self::Positive => 7,
            Sign::Negative => 8
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
