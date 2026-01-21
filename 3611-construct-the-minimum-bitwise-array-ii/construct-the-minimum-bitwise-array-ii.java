class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int val = nums.get(i);
            
            // Edge Case: 2 is the only even prime (binary 10).
            // It has no trailing ones to flip.
            if (val == 2) {
                ans[i] = -1;
            } else {
                // Find the sequence of trailing ones
                int temp = val;
                int trailingOnes = 0;
                
                // Count how many consecutive 1s are at the end
                while ((temp & 1) == 1) {
                    trailingOnes++;
                    temp >>= 1;
                }
                
                // Flip the MSB of the trailing ones sequence to get minimum
                ans[i] = val - (1 << (trailingOnes - 1));
            }
        }
        return ans;
    }
}