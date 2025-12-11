class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        int[] minCol = new int[n + 1];
        int[] maxCol = new int[n + 1];
        int[] minRow = new int[n + 1];
        int[] maxRow = new int[n + 1];

        Arrays.fill(minCol, Integer.MAX_VALUE);
        Arrays.fill(minRow, Integer.MAX_VALUE);

        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            minCol[x] = Math.min(minCol[x], y);
            maxCol[x] = Math.max(maxCol[x], y);
            minRow[y] = Math.min(minRow[y], x);
            maxRow[y] = Math.max(maxRow[y], x);
        }

        int count = 0;
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            if (minCol[x] < y && y < maxCol[x] && minRow[y] < x && x < maxRow[y]) {
                count++;
            }
        }
        return count;
    }
}