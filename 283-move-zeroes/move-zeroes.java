class Solution {
    public void moveZeroes(int[] nums) {
         int k = 0;
        for (int n : nums)
            if (n != 0) nums[k++] = n;
        while (k < nums.length) nums[k++] = 0;        
    }
}