class Solution {
public:
    int minOperations(vector<vector<int>>& A, int x) {
        vector<int> B;
        for (auto &r : A) {
            for (int n : r) B.push_back(n);
        }
        sort(begin(B), end(B));
        long mn = B[0], val = 0;
        for (int i = 0; i < B.size(); ++i) {
            if ((B[i] - mn) % x) return -1;
            val += (B[i] - mn) / x;
        }
        long ans = val;
        for (int i = 1; i < B.size(); ++i) {
            int diff = (B[i] - B[i - 1]) / x;
            val += i * diff - (B.size() - i) * diff;
            ans = min(ans, val);
        }
        return ans;
    }
};