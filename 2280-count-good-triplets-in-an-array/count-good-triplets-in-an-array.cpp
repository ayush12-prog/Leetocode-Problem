class Solution {
public:
    long long goodTriplets(vector<int> &A, vector<int> &B) {
        int N = A.size();
        vector<int> m(N);
        for (int i = 0; i < N; ++i) m[A[i]] = i;
        long long ans = 0;
        vector<int> tree(N + 1);
        for (int i = 1; i < N - 1; ++i) {
            for (int j = m[B[i - 1]] + 1; j <= N; j += j & -j) ++tree[j]; // increment the count of m[B[i-1]]
            int y = m[B[i]], less = 0;
            for (int j = y; j; j &= j - 1) less += tree[j]; // count all the numbers less than m[B[i]]
            ans += (long)less * (N - 1 - y - (i - less));
        }
        return ans;
    }
};