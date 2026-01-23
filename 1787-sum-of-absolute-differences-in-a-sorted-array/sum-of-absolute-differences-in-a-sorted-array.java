class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        int totalSum = 0;
        for (int x : nums) totalSum += x;

        int leftSum = 0;

        for (int i = 0; i < n; i++) {
            int rightSum = totalSum - leftSum - nums[i];

            res[i] = (nums[i] * i - leftSum)
                   + (rightSum - nums[i] * (n - i - 1));

            leftSum += nums[i];
        }
        return res;
    }
}
