class Solution {
public:
    long long maxProfit(vector<int>& prices, vector<int>& strategy, int k) {
        int n = prices.size();
        long long originalProfit = 0;
        for (int i = 0; i < n; i++) {
            originalProfit += (long long)strategy[i] * prices[i];
        }

        long long currentDelta = 0;
        int half = k / 2;

        for (int i = 0; i < half; i++) {
            currentDelta += (long long)(0 - strategy[i]) * prices[i];
        }
        for (int i = half; i < k; i++) {
            currentDelta += (long long)(1 - strategy[i]) * prices[i];
        }

        long long maxDelta = max(0LL, currentDelta);

        for (int i = 1; i <= n - k; i++) {
            currentDelta -= (long long)(0 - strategy[i - 1]) * prices[i - 1];
            
            int mid = i + half - 1;
            currentDelta -= prices[mid];
            
            int end = i + k - 1;
            currentDelta += (long long)(1 - strategy[end]) * prices[end];

            maxDelta = max(maxDelta, currentDelta);
        }

        return originalProfit + maxDelta;
    }
};