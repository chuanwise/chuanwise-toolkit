package cn.chuanwise.common.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串工具类
 *
 * @author Chuanwise
 */
public class Strings
        extends StaticUtilities {
    
    /**
     * 判断字符串是否为空
     *
     * @param string 字符串
     * @return 当字符串为 null 或长度为 0 时返回 true
     */
    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }
    
    /**
     * 判断字符串是否非空
     *
     * @param string 字符串
     * @return 当字符串不为 null 且长度不为 0 返回 true
     */
    public static boolean nonEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * 判断字符串是否为空白
     *
     * @param string 字符串
     * @return 当字符串为空白，即全为 ' ' 时返回 true
     */
    public static boolean isBlank(String string) {
        Preconditions.objectArgumentNonEmpty(string, "string");

        char[] chars = string.toCharArray();
        for (char ch : chars) {
            if (ch != ' ') {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 将一个字符串的内容重复若干次
     *
     * @param string 字符串
     * @param count 重复次数
     * @return 重复后的字符串
     * @throws IllegalArgumentException string 为 null 或 count < 0
     */
    public static String repeat(String string, int count) {
        Preconditions.objectNonNull(string, "string");
        Preconditions.argument(count >= 0, "count must be bigger than or equals to 0!");
        
        if (count == 0 || string.length() == 0) {
            return "";
        }
        
        final StringBuilder stringBuilder = new StringBuilder(string.length() * count);
        for (int i = 0; i < count; i++) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    /**
     * 计算 2 个字符串的最大公共子序列长度
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 最大公共子序列长度
     */
    public static int maxLongestCommonSubsequenceLength(String str1, String str2) {
        Preconditions.objectNonNull(str1, "string");
        Preconditions.objectNonNull(str2, "string");

        int[][] dp = new int[2][str2.length() + 1];
        int maxLength = 0;
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i % 2][j] = 1 + dp[(i - 1) % 2][j - 1];
                } else {
                    dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[i % 2][j - 1]);
                }

                maxLength = Math.max(maxLength, dp[i % 2][j]);
            }
        }
        return maxLength;
    }
    
    /**
     * 寻找字符串中第一个出现的，位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param charSequence 字符集
     * @param beginIndex 起始索引
     * @param defaultIndex 默认索引
     * @return 当在目标字符串中起始索引后找到任何一个字符属于字符集，返回其索引。否则返回默认索引。
     */
    public static int indexOfIncludedCharacter(CharSequence string, CharSequence charSequence, int beginIndex, int defaultIndex) {
        Preconditions.objectNonNull(string, "string");
        Preconditions.objectNonNull(charSequence, "charSequence");
        Preconditions.objectPosition(beginIndex, string.length(), "begin");
    
        final Set<Character> characters = new HashSet<>();
        for (int i = 0; i < charSequence.length(); i++) {
            characters.add(charSequence.charAt(i));
        }
        return indexOfIncludedCharacter(string, characters, beginIndex, defaultIndex);
    }
    
    /**
     * 寻找字符串中第一个出现的，位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param characters 字符集
     * @param beginIndex 起始索引
     * @param defaultIndex 默认索引
     * @return 当在目标字符串中起始索引后找到任何一个字符属于字符集，返回其索引。否则返回默认索引。
     */
    public static int indexOfIncludedCharacter(CharSequence string, Set<Character> characters, int beginIndex, int defaultIndex) {
        Preconditions.objectNonNull(string, "string");
        Preconditions.objectNonNull(characters, "characters");
        Preconditions.objectPosition(beginIndex, string.length(), "begin");
    
        for (int i = beginIndex; i < string.length(); i++) {
            final char ch = string.charAt(i);
            if (characters.contains(ch)) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 寻找字符串中第一个出现的，位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param characters 字符集
     * @param beginIndex 起始索引
     * @return 当在目标字符串中起始索引后找到任何一个字符属于字符集，返回其索引。否则返回 -1。
     */
    public static int indexOfIncludedCharacter(CharSequence string, Set<Character> characters, int beginIndex) {
        return indexOfIncludedCharacter(string, characters, beginIndex, -1);
    }
    
    /**
     * 寻找字符串中第一个出现的，位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param charSequence 字符集
     * @param beginIndex 起始索引
     * @return 当在目标字符串中起始索引后找到任何一个字符属于字符集，返回其索引。否则返回 -1。
     */
    public static int indexOfIncludedCharacter(CharSequence string, CharSequence charSequence, int beginIndex) {
        return indexOfIncludedCharacter(string, charSequence, beginIndex, -1);
    }
    
    /**
     * 寻找字符串中第一个出现的，位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param characters 字符集
     * @return 当在目标字符串中找到任何一个字符属于字符集，返回其索引。否则返回 -1。
     */
    public static int indexOfIncludedCharacter(CharSequence string, Set<Character> characters) {
        return indexOfIncludedCharacter(string, characters, 0, -1);
    }
    
    /**
     * 寻找字符串中第一个出现的，位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param charSequence 字符集
     * @return 当在目标字符串中找到任何一个字符属于字符集，返回其索引。否则返回 -1。
     */
    public static int indexOfIncludedCharacter(CharSequence string, CharSequence charSequence) {
        return indexOfIncludedCharacter(string, charSequence, 0, -1);
    }
    
    /**
     * 寻找字符串中最后一个出现的，位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param charSequence 字符集
     * @param beginIndex 起始索引
     * @param defaultIndex 默认索引
     * @return 当在目标字符串中起始索引后找到任何一个字符属于字符集，返回其索引。否则返回默认索引。
     */
    public static int lastIndexOfIncludedCharacter(CharSequence string, CharSequence charSequence, int beginIndex, int defaultIndex) {
        Preconditions.objectNonNull(string, "string");
        Preconditions.objectNonNull(charSequence, "charSequence");
        Preconditions.objectPosition(beginIndex, string.length(), "begin");
    
        final Set<Character> characters = new HashSet<>();
        for (int i = 0; i < charSequence.length(); i++) {
            characters.add(charSequence.charAt(i));
        }
        return lastIndexOfIncludedCharacter(string, characters, beginIndex, defaultIndex);
    }
    
    /**
     * 寻找字符串中最后一个出现的，位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param characters 字符集
     * @param beginIndex 起始索引
     * @param defaultIndex 默认索引
     * @return 当在目标字符串中起始索引后找到任何一个字符属于字符集，返回其索引。否则返回默认索引。
     */
    public static int lastIndexOfIncludedCharacter(CharSequence string, Set<Character> characters, int beginIndex, int defaultIndex) {
        Preconditions.objectNonNull(string, "string");
        Preconditions.objectNonNull(characters, "characters");
        Preconditions.objectPosition(beginIndex, string.length(), "begin");
    
        for (int i = string.length() - 1; i >= beginIndex; i--) {
            final char ch = string.charAt(i);
            if (characters.contains(ch)) {
                return i;
            }
        }
        return defaultIndex;
    }
    
    /**
     * 寻找字符串中最后一个出现的，位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param characters 字符集
     * @param beginIndex 起始索引
     * @return 当在目标字符串中起始索引后找到任何一个字符属于字符集，返回其索引。否则返回 -1。
     */
    public static int lastIndexOfIncludedCharacter(CharSequence string, Set<Character> characters, int beginIndex) {
        return lastIndexOfIncludedCharacter(string, characters, beginIndex, -1);
    }
    
    /**
     * 寻找字符串中最后一个出现的，位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param charSequence 字符集
     * @param beginIndex 起始索引
     * @return 当在目标字符串中起始索引后找到任何一个字符属于字符集，返回其索引。否则返回 -1。
     */
    public static int lastIndexOfIncludedCharacter(CharSequence string, CharSequence charSequence, int beginIndex) {
        return lastIndexOfIncludedCharacter(string, charSequence, beginIndex, -1);
    }
    
    /**
     * 寻找字符串中最后一个出现的，位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param characters 字符集
     * @return 当在目标字符串中找到任何一个字符属于字符集，返回其索引。否则返回 -1。
     */
    public static int lastIndexOfIncludedCharacter(CharSequence string, Set<Character> characters) {
        return lastIndexOfIncludedCharacter(string, characters, 0, -1);
    }
    
    /**
     * 寻找字符串中最后一个出现的，位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param charSequence 字符集
     * @return 当在目标字符串中找到任何一个字符属于字符集，返回其索引。否则返回 -1。
     */
    public static int lastIndexOfIncludedCharacter(CharSequence string, CharSequence charSequence) {
        return lastIndexOfIncludedCharacter(string, charSequence, 0, -1);
    }
    
    /**
     * 寻找字符串中第一个出现的，不位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param charSequence 字符集
     * @param beginIndex 起始索引
     * @param defaultIndex 默认索引
     * @return 当在目标字符串中起始索引后找到任何一个字符不属于字符集，返回其索引。否则返回默认索引。
     */
    public static int indexOfExcluded(CharSequence string, CharSequence charSequence, int beginIndex, int defaultIndex) {
        Preconditions.objectNonNull(string, "string");
        Preconditions.objectNonNull(charSequence, "charSequence");
        Preconditions.objectPosition(beginIndex, string.length(), "begin");
    
        final Set<Character> characters = new HashSet<>();
        for (int i = 0; i < charSequence.length(); i++) {
            characters.add(charSequence.charAt(i));
        }
        return indexOfExcluded(string, characters, beginIndex, defaultIndex);
    }
    
    /**
     * 寻找字符串中第一个出现的，不位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param characters 字符集
     * @param beginIndex 起始索引
     * @param defaultIndex 默认索引
     * @return 当在目标字符串中起始索引后找到任何一个字符不属于字符集，返回其索引。否则返回默认索引。
     */
    public static int indexOfExcluded(CharSequence string, Set<Character> characters, int beginIndex, int defaultIndex) {
        Preconditions.objectNonNull(string, "string");
        Preconditions.objectNonNull(characters, "characters");
        Preconditions.objectPosition(beginIndex, string.length(), "begin");
    
        for (int i = beginIndex; i < string.length(); i++) {
            final char ch = string.charAt(i);
            if (characters.contains(ch)) {
                return i;
            }
        }
        
        return defaultIndex;
    }
    
    /**
     * 寻找字符串中第一个出现的，不位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param characters 字符集
     * @param beginIndex 起始索引
     * @return 当在目标字符串中起始索引后找到任何一个字符不属于字符集，返回其索引。否则返回 -1。
     */
    public static int indexOfExcluded(CharSequence string, Set<Character> characters, int beginIndex) {
        return indexOfExcluded(string, characters, beginIndex, -1);
    }
    
    /**
     * 寻找字符串中第一个出现的，不位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param charSequence 字符集
     * @param beginIndex 起始索引
     * @return 当在目标字符串中起始索引后找到任何一个字符不属于字符集，返回其索引。否则返回 -1。
     */
    public static int indexOfExcluded(CharSequence string, CharSequence charSequence, int beginIndex) {
        return indexOfExcluded(string, charSequence, beginIndex, -1);
    }
    
    /**
     * 寻找字符串中第一个出现的，不位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param characters 字符集
     * @return 当在目标字符串中找到任何一个字符不属于字符集，返回其索引。否则返回 -1。
     */
    public static int indexOfExcluded(CharSequence string, Set<Character> characters) {
        return indexOfExcluded(string, characters, 0, -1);
    }
    
    /**
     * 寻找字符串中第一个出现的，不位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param charSequence 字符集
     * @return 当在目标字符串中找到任何一个字符不属于字符集，返回其索引。否则返回 -1。
     */
    public static int indexOfExcluded(CharSequence string, CharSequence charSequence) {
        return indexOfExcluded(string, charSequence, 0, -1);
    }
    
    /**
     * 寻找字符串中最后一个出现的，不位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param charSequence 字符集
     * @param beginIndex 起始索引
     * @param defaultIndex 默认索引
     * @return 当在目标字符串中起始索引后找到任何一个字符不属于字符集，返回其索引。否则返回默认索引。
     */
    public static int lastIndexOfExcluded(CharSequence string, CharSequence charSequence, int beginIndex, int defaultIndex) {
        Preconditions.objectNonNull(string, "string");
        Preconditions.objectNonNull(charSequence, "charSequence");
        Preconditions.objectPosition(beginIndex, string.length(), "begin");
    
        final Set<Character> characters = new HashSet<>();
        for (int i = 0; i < charSequence.length(); i++) {
            characters.add(charSequence.charAt(i));
        }
        return lastIndexOfExcluded(string, characters, beginIndex, defaultIndex);
    }
    
    /**
     * 寻找字符串中最后一个出现的，不位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param characters 字符集
     * @param beginIndex 起始索引
     * @param defaultIndex 默认索引
     * @return 当在目标字符串中起始索引后找到任何一个字符不属于字符集，返回其索引。否则返回默认索引。
     */
    public static int lastIndexOfExcluded(CharSequence string, Set<Character> characters, int beginIndex, int defaultIndex) {
        Preconditions.objectNonNull(string, "string");
        Preconditions.objectNonNull(characters, "characters");
        Preconditions.objectPosition(beginIndex, string.length(), "begin");
    
        for (int i = string.length() - 1; i >= beginIndex; i--) {
            final char ch = string.charAt(i);
            if (!characters.contains(ch)) {
                return i;
            }
        }
        return defaultIndex;
    }
    
    /**
     * 寻找字符串中最后一个出现的，不位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param characters 字符集
     * @param beginIndex 起始索引
     * @return 当在目标字符串中起始索引后找到任何一个不字符属于字符集，返回其索引。否则返回 -1。
     */
    public static int lastIndexOfExcluded(CharSequence string, Set<Character> characters, int beginIndex) {
        return lastIndexOfExcluded(string, characters, beginIndex, -1);
    }
    
    /**
     * 寻找字符串中最后一个出现的，不位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param charSequence 字符集
     * @param beginIndex 起始索引
     * @return 当在目标字符串中起始索引后找到任何一个字符不属于字符集，返回其索引。否则返回 -1。
     */
    public static int lastIndexOfExcluded(CharSequence string, CharSequence charSequence, int beginIndex) {
        return lastIndexOfExcluded(string, charSequence, beginIndex, -1);
    }
    
    /**
     * 寻找字符串中最后一个出现的，不位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param characters 字符集
     * @return 当在目标字符串中找到任何一个字符不属于字符集，返回其索引。否则返回 -1。
     */
    public static int lastIndexOfExcluded(CharSequence string, Set<Character> characters) {
        return lastIndexOfExcluded(string, characters, 0, -1);
    }
    
    /**
     * 寻找字符串中最后一个出现的，不位于字符集合中的字符
     *
     * @param string 搜索目标字符串
     * @param charSequence 字符集
     * @return 当在目标字符串中找到任何一个字符不属于字符集，返回其索引。否则返回 -1。
     */
    public static int lastIndexOfExcluded(CharSequence string, CharSequence charSequence) {
        return lastIndexOfExcluded(string, charSequence, 0, -1);
    }
}
