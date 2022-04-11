package cn.chuanwise.common.algorithm;

import cn.chuanwise.common.util.Preconditions;
import cn.chuanwise.common.util.StaticUtilities;

/**
 * 最长公共子串算法
 *
 * @author Chuanwise
 */
public class LongestCommonSubstring
    extends StaticUtilities {
    
    /**
     * 计算两个字符串的最长公共子串
     *
     * @param str1 字符串 1
     * @param str2 字符串 2
     * @return 最长公共子串
     */
    public static String calculate(String str1, String str2) {
        Preconditions.nonNull(str1);
        Preconditions.nonNull(str2);
        
        char[] arrayA = str1.toCharArray();
        char[] arrayB = str2.toCharArray();
        String maxString = "";
        for (int i = 0; i < arrayA.length; i++) {
            for (int j = 0; j < arrayB.length; j++) {
                if (arrayA[i]==arrayB[j]){
                    // i为起点
                    int i1 = i;
                    int j1 = j;
                    // 找到相同的字母后，以当前j为起点，持续比较下去，看相同部分到哪里结束
                    for (; i1 <arrayA.length && j1 < arrayB.length && arrayA[i1]==arrayB[j1]; i1++,j1++) {}
                    // i1为终点
                    if (i!=i1-1){
                        // 不断用common共同子串与max最长子串比较长度，只保留最长那个子串
                        String common = str1.substring(i,i1);
                        if (maxString.length() < common.length()){
                            maxString = common;
                        }
                    }
                }
            }
        }
        
        return maxString;
    }
    
    /**
     * 计算两个字符串的最长公共子串长度
     *
     * @param str1 字符串 1
     * @param str2 字符串 2
     * @return 最长公共子串长度
     */
    public static int length(String str1, String str2) {
        return calculate(str1, str2).length();
    }
}
