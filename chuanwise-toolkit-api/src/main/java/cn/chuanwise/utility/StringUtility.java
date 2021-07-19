package cn.chuanwise.utility;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class StringUtility extends StaticUtility {
    public static boolean isEmpty(String string) {
        return Objects.isNull(string) || string.isEmpty();
    }

    public static boolean isSpace(String string) {
        if (isEmpty(string)) {
            return true;
        }

        for (int i = 0; i < string.length(); i++) {
            if (!Character.isSpaceChar(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String repeat(String what, int times) {
        CheckUtility.checkArgument(times > 0, "repeat time must bigger than 0!");
        final StringBuffer stringBuffer = new StringBuffer(what.length() * times);

        for (int i = 0; i < times; i++) {
            stringBuffer.append(what);
        }

        return stringBuffer.toString();
    }

    /**
     * calculate Hamming Distance between two strings
     *
     * @author
     * @param str1 the 1st string
     * @param str2 the 2nd string
     * @return Hamming Distance between str1 and str2
     */
    public int getHanmingDistance(String str1, String str2) {
        int distance;
        if (str1.length() != str2.length()) {
            distance = -1;
        } else {
            distance = 0;
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }

    /**
     * calculate Hamming weight for binary number
     * @author
     * @param i the binary number
     * @return Hamming weight of the binary number
     */
    public int getHanmingWeight(int i) {
        int n;
        for (n = 0; i > 0; n++) {
            i &= (i - 1);
        }
        return n;
    }

    public static StringBuilder replaceAll(StringBuilder stringBuilder, String from, String to) {
        int position = stringBuilder.indexOf(from);
        while (position != -1) {
            stringBuilder.replace(position, position + from.length(), to);
            position = stringBuilder.indexOf(from, position + to.length());
        }
        return stringBuilder;
    }

    public static String chooseFirstOrSupplie(Predicate<String> chooser, Supplier<String> defaultValueSupplier, String... array) {
        for (String string : array) {
            if (chooser.test(string)) {
                return string;
            }
        }
        return defaultValueSupplier.get();
    }

    public static String chooseFirstNonEmptyOrSupplie(Supplier<String> defaultValueSupplier, String... array) {
        return chooseFirstOrSupplie(string -> !StringUtility.isEmpty(string), defaultValueSupplier, array);
    }
}
