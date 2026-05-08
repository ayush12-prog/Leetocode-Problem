class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        
        // Find the maximum value in the array to size our sieve
        int maxVal = 0;
        for (int x : nums) {
            if (x > maxVal) {
                maxVal = x;
            }
        }
        
        // If the array only contains 1s, we can only take adjacent steps
        if (maxVal < 2) {
            return n - 1;
        }
        
        // Sieve of Eratosthenes to precompute the Smallest Prime Factor (SPF)
        int[] spf = new int[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) {
            spf[i] = i;
        }
        for (int i = 2; i * i <= maxVal; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= maxVal; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
        
        // Identify which primes are actually present in the array as jump sources
        boolean[] isPrimeSource = new boolean[maxVal + 1];
        for (int x : nums) {
            if (x >= 2 && spf[x] == x) {
                isPrimeSource[x] = true;
            }
        }
        
        // Map each prime source to all indices in the array that are multiples of that prime
        List<Integer>[] multiples = new ArrayList[maxVal + 1];
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            int prevPrime = 0;
            
            // Factorize the number using SPF
            while (val > 1) {
                int p = spf[val];
                // Process each distinct prime factor only once
                if (p != prevPrime) {
                    if (isPrimeSource[p]) {
                        if (multiples[p] == null) {
                            multiples[p] = new ArrayList<>();
                        }
                        multiples[p].add(i);
                    }
                    prevPrime = p;
                }
                val /= p;
            }
        }
        
        // Array-based Queue for extreme BFS performance
        int[] queue = new int[n];
        int head = 0, tail = 0;
        
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        
        queue[tail++] = 0;
        dist[0] = 0;
        boolean[] usedPrime = new boolean[maxVal + 1];
        
        while (head < tail) {
            int curr = queue[head++];
            int d = dist[curr];
            
            // Goal reached check
            if (curr == n - 1) return d;
            
            // 1. Forward Adjacent Step
            if (curr + 1 < n && dist[curr + 1] == -1) {
                if (curr + 1 == n - 1) return d + 1;
                dist[curr + 1] = d + 1;
                queue[tail++] = curr + 1;
            }
            
            // 2. Backward Adjacent Step
            if (curr - 1 >= 0 && dist[curr - 1] == -1) {
                dist[curr - 1] = d + 1;
                queue[tail++] = curr - 1;
            }
            
            // 3. Prime Teleportation
            int val = nums[curr];
            if (val >= 2 && spf[val] == val && !usedPrime[val]) {
                usedPrime[val] = true; // Mark prime as used to prevent redundant evaluations
                
                if (multiples[val] != null) {
                    for (int nextIdx : multiples[val]) {
                        if (dist[nextIdx] == -1) {
                            if (nextIdx == n - 1) return d + 1;
                            dist[nextIdx] = d + 1;
                            queue[tail++] = nextIdx;
                        }
                    }
                }
            }
        }
        
        return -1;
    }
}