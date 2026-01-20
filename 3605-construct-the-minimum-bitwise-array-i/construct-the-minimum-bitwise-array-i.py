class Solution:
    def minBitwiseArray(self, nums: List[int]) -> List[int]:
        ans = []
        
        for val in nums:
            if val == 2:
                ans.append(-1)
            else:
                # Find the sequence of trailing ones
                trailing_ones = 0
                temp = val
                
                while (temp & 1) == 1:
                    trailing_ones += 1
                    temp >>= 1
                
                # Flip the highest bit of the trailing ones sequence
                # to get the minimum valid x
                ans.append(val - (1 << (trailing_ones - 1)))
                
        return ans
        