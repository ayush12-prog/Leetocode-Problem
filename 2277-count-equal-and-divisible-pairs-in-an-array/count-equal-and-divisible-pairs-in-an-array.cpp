class Solution {
public:
    int countPairs(vector<int>& A, int k) {
        int N = A.size(), ans = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                ans += A[i] == A[j] && i * j % k == 0;
            }
        }
        return ans;
    }
};