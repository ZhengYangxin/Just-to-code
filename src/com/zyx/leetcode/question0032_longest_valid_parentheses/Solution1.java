package com.zyx.leetcode.question0032_longest_valid_parentheses;

/**
 *
 * 动态规划， 求最长子串长度
 *
 * 1. 子问题 （分支）从后往前看， problem(i) = subproblem(i-1)
 *
 * 2. 定义状态数组
 *      dp[i] = dp [i-1]  //傻，需更具条件分情况
 *      1. 一种从题目得有效则 dp[i] 为 ")", dp[i-1] 为 "("
 *      dp[i]=dp[i−2]+2
 *
 *      2.还有一种"(())"  dp[i] 为 ")"  dp[i-1] 也为 ")" 则 dp[i - dp[i-1] -1] 为"("
 *      dp[i] = dp[i - dp[i-1] -1]
 *
 * dp[i] = dp[i]=dp[i−2]+2 + dp[i - dp[i-1] -1]
 *
 */
public class Solution1 {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int maxans = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1)  == '(') {
                    dp[i] = dp[i - 1] + (dp[i - dp[i - 1]] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

}
