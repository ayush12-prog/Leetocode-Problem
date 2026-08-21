class Solution {
    long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    long count(long x, int[] coins) {
        long ans = 0;
        int n = coins.length;

        for (int mask = 1; mask < (1 << n); mask++) {
            long multiple = 1;
            int cnt = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    multiple = lcm(multiple, coins[i]);
                    cnt++;
                    if (multiple > x) break;
                }
            }

            if (multiple > x) continue;

            if (cnt % 2 == 1) ans += x / multiple;
            else ans -= x / multiple;
        }

        return ans;
    }

    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long high = (long) Arrays.stream(coins).min().getAsInt() * k;

        while (low < high) {
            long mid = (low + high) / 2;
            if (count(mid, coins) >= k) high = mid;
            else low = mid + 1;
        }

        return low;
    }
}