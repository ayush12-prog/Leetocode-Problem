class Solution {
    public String countAndSay(int n) {
        String s = "1";
        
        for (int step = 2; step <= n; step++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            
            for (int i = 0; i < s.length(); i++) {
                // If next character is the same, increment the count
                if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                    count++;
                } else {
                    // Group ended; append count followed by the digit
                    sb.append(count).append(s.charAt(i));
                    count = 1; // reset for next group
                }
            }
            s = sb.toString();
        }
        
        return s;
    }
}