class Solution {
    public int longestPalindrome(String s, String t) {
        String r=reverse(t);
        int[]l1=new int[s.length()];
        int[]l2=new int[r.length()];
        for(int i=0;i<s.length();i++){
            l1[i]=f1(s.substring(i,s.length()));
        }
         for(int i=0;i<r.length();i++){
            l2[i]=f1(r.substring(i,r.length()));
        }
        int ans=0;
        for(int i=0;i<s.length();i++){
            String pp=s.substring(i,s.length());
            int[] z=f(pp+"#"+r);
            for(int j=pp.length()+1;j<pp.length()+1+r.length();j++){
              int idx=j-pp.length()-1;
              int ll=z[j];
                int ll1=0;
                int ll2=0;
                if(i+ll<s.length())ll1=l1[i+ll];
                if(idx+ll<r.length())ll2=l2[idx+ll];
              ans=Math.max(ans,ll*2+Math.max(ll1,ll2));
            }
        }
        return ans;
    }
  public String reverse(String s){
        String ans="";
        for(char ch:s.toCharArray()){
            ans=ch+ans;
        }
        return ans;
    }

    public int[] f(String s) {
        int n = s.length();
        int[] Z = new int[n];
        int L = 0, R = 0; 

        for (int i = 1; i < n; i++) {
            if (i <= R) {
                Z[i] = Math.min(R - i + 1, Z[i - L]); 
            }
            while (i + Z[i] < n && s.charAt(Z[i]) == s.charAt(i + Z[i])) {
                Z[i]++; 
            }
            if (i + Z[i] - 1 > R) { 
                L = i;
                R = i + Z[i] - 1;
            }
        }
        return Z;
}
       public int f1(String s){
            if (s == null || s.length() == 0) return 0;

        String rev = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev;

        int n = combined.length();
        int[] lps = new int[n]; 
        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && combined.charAt(i) != combined.charAt(j)) {
                j = lps[j - 1];
            }
            if (combined.charAt(i) == combined.charAt(j)) {
                j++;
                lps[i] = j;
            }
        }

       
        return lps[n - 1];
    }

}