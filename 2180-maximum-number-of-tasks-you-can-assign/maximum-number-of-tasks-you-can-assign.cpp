class Solution {
public:
    int maxTaskAssign(vector<int>& T, vector<int>& W, int P, int S) {
        sort(begin(T), end(T));
        sort(begin(W), end(W));
        auto valid = [&](int cnt) {
            multiset<int> s(begin(W), end(W));
            int match = 0;
            bool ans = true;
            for (int i = cnt - 1; i >= 0; --i) {
                if (T[i] <= *s.rbegin()) {
                    s.erase(prev(s.end()));
                } else {
                    auto it = s.lower_bound(T[i] - S);
                    if (it != s.end()) {
                        ++match;
                        s.erase(it);
                    } else {
                        ans = false;
                        break;
                    }
                }
                if (match > P) {
                    ans = false;
                    break;
                }
            }
            return ans;
        };
        int L = 0, R = min(T.size(), W.size());
        while (L <= R) {
            int M = (L + R) / 2;
            if (valid(M)) L = M + 1;
            else R = M - 1;
        }
        return R;
    }
};