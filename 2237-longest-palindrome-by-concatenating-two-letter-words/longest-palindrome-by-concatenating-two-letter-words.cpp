class Solution {
public:
    int longestPalindrome(vector<string>& A) {
        unordered_map<string, int> m;
        for (auto &s : A) m[s]++;
        int ans = 0, even = 0, odd = 0;
        for (auto &[s, cnt] : m) {
            if (s[0] == s[1]) {
                if (cnt % 2) odd = 1;
                even += cnt - cnt % 2;
            } else {
                string r(rbegin(s), rend(s));
                if (m.count(r)) {
                    ans += min(cnt, m[r]) * 2;
                    cnt = 0;
                }
            }
        }
        return (ans + even + odd) * 2;
    }
};