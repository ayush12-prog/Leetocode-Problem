public class Solution {
    public int reverseDegree(String s) {
        int result = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int reversedAlphabetIndex = 'z' - ch + 1; // Compute reverse position
            int positionInString = i + 1; // 1-based index
            result += reversedAlphabetIndex * positionInString;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.reverseDegree("abc")); // Output: 148
        System.out.println(sol.reverseDegree("zaza")); // Output: 160
    }
}
