class Solution {
    public double myPow(double x, int n) {

        long power = n;

        if (power < 0) {
            power = -power;
        }

        double ans = 1.0;

        for ( ;power > 0; power /= 2) {

            if (power % 2 == 1) {
                ans = ans * x;
            }

            x = x * x;
        }

        if (n < 0) {
            return 1.0 / ans;
        }

        return ans;
    }
}