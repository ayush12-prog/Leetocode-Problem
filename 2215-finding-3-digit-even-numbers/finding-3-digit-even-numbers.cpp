class Solution {
public:
    vector<int> findEvenNumbers(vector<int>& A) {
        int cnt[10] = {};
        for (int n : A) cnt[n]++;
        vector<int> ans;
        function<void(int, int)> dfs = [&](int i, int n) {
            if (i == 3) {
                ans.push_back(n);
                return;
            }
            for (int d = 0; d <= 9; ++d) {
                if (cnt[d] == 0 || (i == 0 && d == 0) || (i == 2 && d % 2)) continue;
                cnt[d]--;
                dfs(i + 1, n * 10 + d);
                cnt[d]++;
            }
        };
        dfs(0, 0);
        return ans;
    }
};