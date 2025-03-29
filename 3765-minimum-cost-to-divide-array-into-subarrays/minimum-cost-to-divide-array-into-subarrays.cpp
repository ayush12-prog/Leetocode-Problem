#include <vector>
#include <climits>
#include <algorithm>
#include <cmath>

class Solution {
public:
    // Function to calculate the intersection point of two lines
    long double calculateIntersection(long long m1, long long b1, long long m2, long long b2) {
        return static_cast<long double>(b2 - b1) / (m1 - m2);
    }

    // Function to add a line to the convex hull
    void addLine(std::vector<std::pair<long long, long long>>& hull,
                 std::vector<long double>& intersectionPoints,
                 long long m, long long b) {
        while (!hull.empty()) {
            if (hull.back().first == m) {
                if (hull.back().second <= b) return;
                else hull.pop_back();
            } else {
                long double x = calculateIntersection(hull.back().first, hull.back().second, m, b);
                if (!intersectionPoints.empty() && x <= intersectionPoints.back()) {
                    hull.pop_back();
                    intersectionPoints.pop_back();
                } else {
                    intersectionPoints.push_back(x);
                    break;
                }
            }
        }
        if (hull.empty()) intersectionPoints.push_back(-1e18);
        hull.push_back({m, b});
    }

    // Function to query the convex hull for a given x
    long long queryHull(const std::vector<std::pair<long long, long long>>& hull,
                         const std::vector<long double>& intersectionPoints,
                         long long x, int& pt) {
        while (pt + 1 < hull.size() && intersectionPoints[pt + 1] <= x) pt++;
        return hull[pt].first * x + hull[pt].second;
    }

    // Function to compute the minimum cost
    long long computeMinimumCost(const std::vector<int>& n, const std::vector<int>& c, int k) {
        int N = n.size();
        std::vector<long long> prefixN(N + 1, 0), prefixC(N + 1, 0);
        for (int i = 1; i <= N; i++) {
            prefixN[i] = prefixN[i - 1] + n[i - 1];
            prefixC[i] = prefixC[i - 1] + c[i - 1];
        }

        const long long INF = 1LL << 60;
        std::vector<long long> dp(N + 1, INF), dpPrev(N + 1, INF);
        dpPrev[0] = 0;
        long long minCost = INF;

        for (int j = 1; j <= N; j++) {
            std::vector<std::pair<long long, long long>> convexHull;
            std::vector<long double> intersectionPoints;
            int pointIndex = 0;

            for (int l = j - 1; l < N; l++) {
                if (dpPrev[l] < INF) {
                    addLine(convexHull, intersectionPoints, -prefixC[l], dpPrev[l]);
                }
            }

            for (int i = j; i <= N; i++) {
                long long x = prefixN[i] + static_cast<long long>(k) * j;
                dp[i] = x * prefixC[i] + queryHull(convexHull, intersectionPoints, x, pointIndex);
            }

            dpPrev = dp;
            minCost = std::min(minCost, dp[N]);
        }
        return minCost;
    }

    // Main function to calculate the minimum cost
    long long minimumCost(std::vector<int>& n, std::vector<int>& c, int k) {
        return computeMinimumCost(n, c, k);
    }
};