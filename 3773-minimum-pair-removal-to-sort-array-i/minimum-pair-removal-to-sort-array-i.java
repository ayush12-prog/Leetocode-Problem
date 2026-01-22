class Solution {
    public int minimumPairRemoval(int[] nums) {
        // Convert array to ArrayList for easy removal/insertion
        List<Integer> list = new ArrayList<>();
        for (int n : nums) list.add(n);

        int ops = 0;

        // Loop until the list is sorted
        while (!isSorted(list)) {
            int minSum = Integer.MAX_VALUE;
            int minIdx = -1;

            // Find the adjacent pair with the minimum sum
            for (int i = 0; i < list.size() - 1; i++) {
                int sum = list.get(i) + list.get(i + 1);
                
                // Strictly less than ensures we pick the leftmost in case of tie
                if (sum < minSum) {
                    minSum = sum;
                    minIdx = i;
                }
            }

            // Perform the merge operation
            int newVal = list.get(minIdx) + list.get(minIdx + 1);
            list.remove(minIdx);     // Remove first element of pair
            list.remove(minIdx);     // Remove second element (index shifts)
            list.add(minIdx, newVal); // Insert sum
            
            ops++;
        }
        return ops;
    }

    // Helper function to check if list is sorted
    private boolean isSorted(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) return false;
        }
        return true;
    }
}
  