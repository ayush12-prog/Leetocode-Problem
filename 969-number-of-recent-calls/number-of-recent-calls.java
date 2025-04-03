class RecentCounter {
    private Deque<Integer> q = new ArrayDeque<>();

    public RecentCounter() {
    }

    public int ping(int t) {
        q.offer(t);
        while (q.peekFirst() < t - 3000) {
            q.pollFirst();
        }
        return q.size();
    }
}
