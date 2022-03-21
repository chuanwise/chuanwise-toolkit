package cn.chuanwise.common.algorithm;

import cn.chuanwise.common.util.Preconditions;
import cn.chuanwise.common.util.StaticUtilities;

/**
 * 最长公共子序列算法
 *
 * @author Chuanwise
 */
public class LongestCommonSubsequence
    extends StaticUtilities {
    
    /**
     * 计算两个字符串的最长公共子序列长度
     *
     * @param str1 字符串 1
     * @param str2 字符串 2
     * @return 最长公共子序列长度
     */
    public static int length(String str1, String str2) {
        Preconditions.argumentNonNull(str1);
        Preconditions.argumentNonNull(str2);
        
        final int n = str1.length();
        final int m = str2.length();
        
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = 0;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }
}
