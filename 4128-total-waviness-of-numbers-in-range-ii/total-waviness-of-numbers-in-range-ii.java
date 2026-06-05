class Solution {
    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long num) {
        if (num < 100) return 0;
        
        String s = String.valueOf(num);
        long[][][][] memo = new long[s.length()][11][11][];
        
        return dfs(0, -1, -1, true, true, s, memo)[1];
    }

    private long[] dfs(int idx, int prev2, int prev1, boolean isLimit, boolean isLead, String s, long[][][][] memo) {
        if (idx == s.length()) {
            return new long[]{1, 0};
        }
        
        if (!isLimit && !isLead && memo[idx][prev2 + 1][prev1 + 1] != null) {
            return memo[idx][prev2 + 1][prev1 + 1];
        }

        long count = 0;
        long sum = 0;
        int upper = isLimit ? s.charAt(idx) - '0' : 9;

        for (int d = 0; d <= upper; d++) {
            boolean nextLead = isLead && (d == 0);
            int nextPrev1 = nextLead ? -1 : d;
            int nextPrev2 = nextLead ? -1 : prev1;

            long[] res = dfs(idx + 1, nextPrev2, nextPrev1, isLimit && (d == upper), nextLead, s, memo);

            int waveAdded = 0;
            if (!isLead && prev2 != -1 && prev1 != -1) {
                if (prev2 < prev1 && prev1 > d) waveAdded = 1;
                if (prev2 > prev1 && prev1 < d) waveAdded = 1;
            }

            count += res[0];
            sum += res[1] + res[0] * waveAdded;
        }

        long[] result = new long[]{count, sum};
        
        if (!isLimit && !isLead) {
            memo[idx][prev2 + 1][prev1 + 1] = result;
        }
        
        return result;
    }
}