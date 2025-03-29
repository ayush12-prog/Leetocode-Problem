class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        String ss = "1" + s + "1";
        int count = 0;
        int maxbtz = 0;

        for (int i = 0; i < ss.length(); i++) {
            if (ss.charAt(i) == '1') {
                count++;
            }
        }
        maxbtz = count;
        
        int max = maxbtz-2;
        int j = 1;
        
        while (j < ss.length() && ss.charAt(j) != '0') {
            j++;
        }
        
        int start = j;
        boolean one = false;
        int nxtstart = j;
        
        while (j < ss.length()) {
            if (ss.charAt(j) == '0') {
                j++;
                continue;
            } else {
                if (one) {
                    max = Math.max(max, count + (j - start)-2);
                    start = nxtstart;
                    count = maxbtz;
                }
                one = true;
                
                while (j < ss.length() && ss.charAt(j) != '0') {
                    j++;
                    count--;
                }
                nxtstart = j;
            }
        }
        return max;
    }
}