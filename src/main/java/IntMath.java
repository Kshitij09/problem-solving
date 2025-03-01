public class IntMath {
    public static int sqrt(int x) {
        int low=0, high=x;
        while (low <= high) {
           int mid = low + (high - low) / 2;
           long sqr = (long) mid * mid;
           if (sqr == x) {
               return mid;
           } else if (sqr < x) {
               low = mid + 1;
           } else {
               high = mid - 1;
           }
        }
        return high;
    }

    public static int ceilDiv(int x, int y) {
        return (x+y-1)/y;
    }

    public static int pow(int x, int n) {
        int base = x;
        int result = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                result *= base;
            }
            base *= base;
            n /= 2;
        }
        return result;
    }
}
