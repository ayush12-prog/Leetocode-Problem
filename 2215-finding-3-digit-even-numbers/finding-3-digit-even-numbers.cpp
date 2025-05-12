class Solution {
public:
    vector<int> findEvenNumbers(vector<int>& A) {
        set<int> s;
        int N = A.size();
        for (int i = 0; i < N; ++i) {
            if (A[i] == 0) continue;
            for (int j = 0; j < N; ++j) {
                if (i == j) continue;
                for (int k = 0; k < N; ++k) {
                    if (k == i || k == j) continue;
                    if (A[k] % 2) continue;
                    s.insert(A[i] * 100 + A[j] * 10 + A[k]);
                }
            }
        }
        return vector<int>(begin(s), end(s));
    }
};