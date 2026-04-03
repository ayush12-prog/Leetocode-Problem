class Solution {
    class Robot implements Comparable<Robot> {
        long pos, dist;
        
        public Robot(long pos, long dist) {
            this.pos = pos;
            this.dist = dist;
        }
        
        public int compareTo(Robot other) {
            return Long.compare(this.pos, other.pos);
        }
    }

    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        Robot[] robs = new Robot[n];
        for (int i = 0; i < n; i++) {
            robs[i] = new Robot(robots[i], distance[i]);
        }
        Arrays.sort(robs);
        Arrays.sort(walls);

        int[] fWalls = new int[walls.length];
        int fCount = 0;
        int baseWalls = 0;

        for (int w : walls) {
            int left = 0, right = n - 1;
            boolean found = false;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (robs[mid].pos == w) {
                    found = true;
                    break;
                } else if (robs[mid].pos < w) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if (found) {
                baseWalls++;
            } else {
                fWalls[fCount++] = w;
            }
        }
        
        int[] filteredWalls = Arrays.copyOf(fWalls, fCount);

        int[][] dp = new int[n + 1][2];

        int cL1 = countWalls(filteredWalls, robs[0].pos - robs[0].dist, robs[0].pos - 1);
        dp[1][0] = cL1;
        dp[1][1] = 0;

        for (int i = 2; i <= n; i++) {
            long R_prev = robs[i-2].pos;
            long D_prev = robs[i-2].dist;
            long R_curr = robs[i-1].pos;
            long D_curr = robs[i-1].dist;

            long x = Math.min(R_curr - 1, R_prev + D_prev);
            int cR = countWalls(filteredWalls, R_prev + 1, x);

            long y = Math.max(R_prev + 1, R_curr - D_curr);
            int cL = countWalls(filteredWalls, y, R_curr - 1);

            int cB = 0;
            if (x >= y - 1) { 
                cB = countWalls(filteredWalls, R_prev + 1, R_curr - 1);
            } else {
                cB = cR + cL;
            }

            dp[i][0] = Math.max(dp[i-1][0] + cL, dp[i-1][1] + cB);
            dp[i][1] = Math.max(dp[i-1][0] + 0, dp[i-1][1] + cR);
        }

        int cRn = countWalls(filteredWalls, robs[n-1].pos + 1, robs[n-1].pos + robs[n-1].dist);
        
        int maxDp = Math.max(dp[n][0], dp[n][1] + cRn);

        return baseWalls + maxDp;
    }

    private int countWalls(int[] arr, long L, long R) {
        if (L > R || arr.length == 0) return 0;
        
        int leftIdx = lowerBound(arr, L);
        int rightIdx = upperBound(arr, R) - 1;

        if (leftIdx <= rightIdx) {
            return rightIdx - leftIdx + 1;
        }
        return 0;
    }

    private int lowerBound(int[] arr, long target) {
        int left = 0, right = arr.length - 1;
        int ans = arr.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private int upperBound(int[] arr, long target) {
        int left = 0, right = arr.length - 1;
        int ans = arr.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}