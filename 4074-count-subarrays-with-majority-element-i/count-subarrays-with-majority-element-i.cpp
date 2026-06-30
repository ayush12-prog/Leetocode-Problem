constexpr int N=2002;
int cntB[N], bias;
class Solution {
public:
    static int countMajoritySubarrays(vector<int>& nums, int target) {
        bias=nums.size()+1;
        int balance=bias;
        memset(cntB , 0, (2*bias)*sizeof(int));
        cntB[balance]=1;
        int ans=0, sum=0;
        
        for (int x : nums) {
            if (x==target) sum+=cntB[balance++];
            else sum-=cntB[--balance];
            
            cntB[balance]++;
            ans+=sum;
        }
        return ans;
    }
};