class Solution {
    int[] parent, size;
    TreeSet<Integer>[] onlineStations;
    boolean[] offline;

    int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return;
        if (size[pa] < size[pb]) {
            int t = pa;
            pa = pb;
            pb = t;
        }
        parent[pb] = pa;
        size[pa] += size[pb];
        onlineStations[pa].addAll(onlineStations[pb]);
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        parent = new int[c + 1];
        size = new int[c + 1];
        offline = new boolean[c + 1];
        onlineStations = new TreeSet[c + 1];

        for (int i = 1; i <= c; i++) {
            parent[i] = i;
            size[i] = 1;
            onlineStations[i] = new TreeSet<>();
            onlineStations[i].add(i);
        }

        for (int[] e : connections) union(e[0], e[1]);

        ArrayList<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            int type = q[0], x = q[1];
            int px = find(x);
            if (type == 1) {
                if (!offline[x]) ans.add(x);
                else {
                    if (onlineStations[px].isEmpty()) ans.add(-1);
                    else ans.add(onlineStations[px].first());
                }
            } else {
                if (!offline[x]) {
                    offline[x] = true;
                    onlineStations[find(x)].remove(x);
                }
            }
        }

        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) res[i] = ans.get(i);
        return res;
    }
}