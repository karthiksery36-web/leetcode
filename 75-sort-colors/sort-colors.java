class Solution {
    public void sortColors(int[] nums) {
         int l = 0, m = 0, r = nums.length - 1;
        while (m <= r) {
            if (nums[m] == 0) {
                int t = nums[l]; nums[l] = nums[m]; nums[m] = t;
                l++; m++;
            } else if (nums[m] == 1) {
                m++;
            } else {
                int t = nums[m]; nums[m] = nums[r]; nums[r] = t;
                r--;
            }
        }
    }
}