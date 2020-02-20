package com.zyx.leetcode.question0053_max_sub_array;

/**
 * 动态规划
 *
 * 1. 分治（子问题）值   子序列长度
 * 2. 状态数组定义 dp[i][j] =
 * 3. DP方程式
 *
 * 执行耗时:1 ms,击败了99.96% 的Java用户，内存消耗:47.7 MB,击败了5.00% 的Java用户
 *
 */
public class Solution0 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i - 1], 0) + nums[i];
            max = Math.max(nums[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution0 solution1 = new Solution0();
        int result = solution1.maxSubArray(new int[]{1});
        System.out.println("result = " + result);

    }
}
