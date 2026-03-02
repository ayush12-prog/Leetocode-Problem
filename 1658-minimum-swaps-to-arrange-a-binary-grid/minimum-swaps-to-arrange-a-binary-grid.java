class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] zeros = new int[n];
        
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    count++;
                } else {
                    break;
                }
            }
            zeros[i] = count;
        }
        
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            int target = n - 1 - i;
            int j = i;
            
            while (j < n && zeros[j] < target) {
                j++;
            }
            
            if (j == n) {
                return -1;
            }
            
            swaps += (j - i);
            int temp = zeros[j];
            while (j > i) {
                zeros[j] = zeros[j - 1];
                j--;
            }
            zeros[i] = temp;
        }
        
        return swaps;
    }
}