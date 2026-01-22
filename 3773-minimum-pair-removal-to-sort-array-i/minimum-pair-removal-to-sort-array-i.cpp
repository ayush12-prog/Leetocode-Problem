class Solution {
public:
    int minimumPairRemoval(vector<int>& nums) {
        int ops = 0;
        while (true) {
            // Check if sorted
            bool sorted = true;
            for (int i = 0; i < nums.size() - 1; ++i) {
                if (nums[i] > nums[i+1]) {
                    sorted = false;
                    break;
                }
            }
            if (sorted) return ops;

            // Find min pair
            int minSum = INT_MAX;
            int idx = -1;
            for (int i = 0; i < nums.size() - 1; ++i) {
                long long currentSum = (long long)nums[i] + nums[i+1];
                if (currentSum < minSum) {
                    minSum = currentSum;
                    idx = i;
                }
            }

            // Replace pair with sum
            int newVal = nums[idx] + nums[idx+1];
            nums.erase(nums.begin() + idx);
            nums.erase(nums.begin() + idx);
            nums.insert(nums.begin() + idx, newVal);
            ops++;
        }
    }
};
  