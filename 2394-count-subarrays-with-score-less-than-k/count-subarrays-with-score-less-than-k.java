class Solution {
    public long countSubarrays(int[] nums, long k) {
        long sum = 0;
        long count = 0;
        int i = 0;
        int j = 0;
        while (i < nums.length) {
            sum += nums[i];
            while (sum * (i - j + 1) >= k) {
                sum -= nums[j++];
            }
            count += i++ - j + 1;
        }
        return count;
    }
}