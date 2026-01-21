class Solution {
public:
    vector<int> minBitwiseArray(vector<int>& nums) {
        vector<int> ans;
        
        for (int val : nums) {
            if (val == 2) {
                ans.push_back(-1);
            } else {
                int trailingOnes = 0;
                int temp = val;
                
                // Count trailing 1s
                while ((temp & 1) == 1) {
                    trailingOnes++;
                    temp >>= 1;
                }
                
                // Flip the MSB of the trailing ones sequence
                ans.push_back(val - (1 << (trailingOnes - 1)));
            }
        }
        return ans;
    }
};