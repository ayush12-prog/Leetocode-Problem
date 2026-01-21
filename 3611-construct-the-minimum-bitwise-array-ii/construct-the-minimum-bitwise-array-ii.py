class Solution:
    def minBitwiseArray(self, nums: List[int]) -> List[int]:
        ans = []
        
        for val in nums:
            if val == 2:
                ans.append(-1)
            else:
                trailing_ones = 0
                temp = val
                
                # Count trailing 1s
                while (temp & 1) == 1:
                    trailing_ones += 1
                    temp >>= 1
                
                # Flip the MSB of the trailing ones sequence
                ans.append(val - (1 << (trailing_ones - 1)))
                
        return ans