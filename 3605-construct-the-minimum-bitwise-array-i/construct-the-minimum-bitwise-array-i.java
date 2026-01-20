class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int val = nums.get(i);
            
            // Edge Case: 2 is the only even prime.
            // It has no trailing ones to flip, so it's impossible.
            if (val == 2) {
                ans[i] = -1;
            } else {
                // Find the position of the first zero from the right
                // Since 'val' is a prime > 2, it is always odd, so it ends in 1.
                int temp = val;
                int trailingOnes = 0;
                
                // Count how many consecutive 1s are at the end
                while ((temp & 1) == 1) {
                    trailingOnes++;
                    temp >>= 1;
                }
                
                // We minimize the number by flipping the highest bit 
                // in that trailing sequence of 1s.
                // This bit is at index (trailingOnes - 1).
                ans[i] = val - (1 << (trailingOnes - 1));
            }
        }
        return ans;
    }
}