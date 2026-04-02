class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        
        int[][][] dp = new int[m][n][3];
        int MIN_VAL = (int) -1e9;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = MIN_VAL;
                dp[i][j][1] = MIN_VAL;
                dp[i][j][2] = MIN_VAL;
            }
        }
        
        dp[0][0][0] = coins[0][0];
        if (coins[0][0] < 0) {
            dp[0][0][1] = 0;
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                
                int val = coins[i][j];
                
                for (int k = 0; k < 3; k++) {
                    int prev = MIN_VAL;
                    
                    if (i > 0) prev = Math.max(prev, dp[i - 1][j][k]);
                    if (j > 0) prev = Math.max(prev, dp[i][j - 1][k]);
                    
                    if (prev != MIN_VAL) {
                        dp[i][j][k] = prev + val;
                    }
                    
                    if (val < 0 && k > 0) {
                        int prevKMinus1 = MIN_VAL;
                        if (i > 0) prevKMinus1 = Math.max(prevKMinus1, dp[i - 1][j][k - 1]);
                        if (j > 0) prevKMinus1 = Math.max(prevKMinus1, dp[i][j - 1][k - 1]);
                        
                        if (prevKMinus1 != MIN_VAL) {
                            dp[i][j][k] = Math.max(dp[i][j][k], prevKMinus1);
                        }
                    }
                }
            }
        }
        
        return Math.max(dp[m - 1][n - 1][0], Math.max(dp[m - 1][n - 1][1], dp[m - 1][n - 1][2]));
    }
}