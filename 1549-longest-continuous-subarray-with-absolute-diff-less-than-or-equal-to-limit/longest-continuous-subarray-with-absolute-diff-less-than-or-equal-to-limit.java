import java.util.*;

class Solution {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxD = new LinkedList<>();
        Deque<Integer> minD = new LinkedList<>();

        int left = 0, res = 0;

        for (int right = 0; right < nums.length; right++) {
            while (!maxD.isEmpty() && nums[maxD.peekLast()] < nums[right]) {
                maxD.pollLast();
            }
            while (!minD.isEmpty() && nums[minD.peekLast()] > nums[right]) {
                minD.pollLast();
            }

            maxD.offerLast(right);
            minD.offerLast(right);

            while (nums[maxD.peekFirst()] - nums[minD.peekFirst()] > limit) {
                if (maxD.peekFirst() == left) maxD.pollFirst();
                if (minD.peekFirst() == left) minD.pollFirst();
                left++;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}