class SmallestInfiniteSet {
    private int[] set = new int[1001];
    private int ptr = 1;

    public SmallestInfiniteSet() {
        for (int i = 1; i <= 1000; i++) {
            set[i] = 1;
        }
    }

    public int popSmallest() {
        int val = -1;
        if (ptr > 0 && ptr <= 1000) {
            set[ptr] = 0;
            val = ptr++;
            while (ptr <= 1000 && set[ptr] == 0) {
                ptr++;
            }
        }
        return val;
    }

    public void addBack(int num) {
        if (set[num] == 0) {
            set[num] = 1;
            if (num < ptr) {
                ptr = num;
            }
        }
    }
}