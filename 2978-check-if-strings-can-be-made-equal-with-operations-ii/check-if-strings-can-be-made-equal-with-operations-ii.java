class Solution {
    public boolean checkStrings(String s1, String s2) {
        int[][] counts = new int[2][26];
        int n = s1.length();
        
        for (int i = 0; i < n; i++) {
            counts[i % 2][s1.charAt(i) - 'a']++;
            counts[i % 2][s2.charAt(i) - 'a']--;
        }
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 26; j++) {
                if (counts[i][j] != 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
}