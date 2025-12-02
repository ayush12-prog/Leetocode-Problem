class Solution {
    static final long MOD = 1000000007;

    public int countTrapezoids(int[][] points) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int[] p : points) {
            int y = p[1];
            freq.put(y, freq.getOrDefault(y, 0) + 1);
        }

        long sum = 0, sumSq = 0;
        for (int c : freq.values()) {
            if (c >= 2) {
                long w = (long)c * (c - 1) / 2;
                sum = (sum + w) % MOD;
                sumSq = (sumSq + (w * w) % MOD) % MOD;
            }
        }

        if (sum == 0) return 0;

        long ans = (sum * sum % MOD - sumSq + MOD) % MOD;
        ans = ans * ((MOD + 1) / 2) % MOD;

        return (int) ans;
    }
}