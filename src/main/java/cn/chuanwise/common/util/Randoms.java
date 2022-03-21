package cn.chuanwise.common.util;

import java.util.Random;

/**
 * 随机数工具类
 *
 * @author Chuanwise
 */
public class Randoms
    extends StaticUtilities {
    
    /**
     * 随机数对象
     */
    private static Random RANDOM = new Random();
    
    /**
     * 使用某个随机数种子刷新随机数工具
     *
     * @param seed 随机数种子
     */
    public static void flush(long seed) {
        RANDOM = new Random(seed);
    }
    
    /**
     * 随机生成一个随机数种子，再用这个种子刷新随机数对象
     */
    public static void flush() {
        flush((long) (Math.random() * System.currentTimeMillis()));
    }
    
    /**
     * 用一个指定的随机数生成器刷新
     *
     * @param random 随机数生成器
     */
    public static void flush(Random random) {
        Preconditions.argumentNonNull(random, "random");
        
        Randoms.RANDOM = random;
    }
    
    /**
     * 获得随机数生成器
     *
     * @return 随机数生成器
     */
    public static Random getRandom() {
        return RANDOM;
    }
    
    /**
     * 生成一个随机数
     *
     * @return 随机数
     */
    public static byte nextByte() {
        return ((byte) RANDOM.nextInt());
    }
    
    /**
     * 生成一个随机数
     *
     * @param bound 最大值
     * @return 随机数
     */
    public static byte nextByte(byte bound) {
        Preconditions.argument(bound > 0, "bound can not be smaller than or equals to 0!");
        return ((byte) (nextInt() % bound));
    }
    
    /**
     * 生成一些随机数
     *
     * @param length 长度
     * @param bound  最大值
     * @return 随机数数组
     */
    public static byte[] nextBytes(int length, byte bound) {
        Preconditions.argument(length >= 0, "byte length must be greater than or equals to 0!");
        Preconditions.argument(bound >= 0, "bound must be greater than or equals to 0!");
    
        final byte[] bytes = new byte[length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = nextByte(bound);
        }
        return bytes;
    }
    
    /**
     * 生成一些随机数
     *
     * @param length 长度
     * @return 随机数数组
     */
    public static byte[] nextBytes(int length) {
        Preconditions.argument(length >= 0, "byte length must be greater than or equals to 0!");
    
        final byte[] bytes = new byte[length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = nextByte();
        }
        return bytes;
    }
    
    /**
     * 生成一个随机数
     *
     * @return 随机数
     */
    public static short nextShort() {
        return (short) RANDOM.nextInt();
    }
    
    /**
     * 生成一个随机数
     *
     * @param bound 上限
     * @return 随机数
     */
    public static short nextShort(short bound) {
        Preconditions.argument(bound > 0, "bound can not be smaller than or equals to 0!");
        return (short) (RANDOM.nextInt() % bound);
    }
    
    /**
     * 生成一些随机数
     *
     * @param length 长度
     * @param bound  上限
     * @return 随机数数组
     */
    public static short[] nextShorts(int length, short bound) {
        Preconditions.argument(length >= 0, "short length must be greater than or equals to 0!");
        Preconditions.argument(bound >= 0, "bound must be greater than or equals to 0!");
    
        final short[] shorts = new short[length];
        for (int i = 0; i < shorts.length; i++) {
            shorts[i] = nextShort(bound);
        }
        return shorts;
    }
    
    /**
     * 生成一些随机数
     *
     * @param length 长度
     * @return 随机数数组
     */
    public static short[] nextShorts(int length) {
        Preconditions.argument(length >= 0, "short length must be greater than or equals to 0!");
    
        final short[] shorts = new short[length];
        for (int i = 0; i < shorts.length; i++) {
            shorts[i] = nextShort();
        }
        return shorts;
    }
    
    /**
     * 生成一个随机数
     *
     * @return 随机数
     */
    public static int nextInt() {
        return RANDOM.nextInt();
    }
    
    /**
     * 生成一个随机数
     *
     * @param bound 上限
     * @return 随机数
     */
    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }
    
    /**
     * 生成一些随机数
     *
     * @param length 长度
     * @param bound  上限
     * @return 随机数数组
     */
    public static int[] nextInts(int length, int bound) {
        Preconditions.argument(length >= 0, "int length must be greater than or equals to 0!");
        Preconditions.argument(bound >= 0, "bound must be greater than or equals to 0!");
    
        final int[] ints = new int[length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = nextInt(bound);
        }
        return ints;
    }
    
    /**
     * 生成一些随机数
     *
     * @param length 长度
     * @return 随机数数组
     */
    public static int[] nextInts(int length) {
        Preconditions.argument(length >= 0, "int length must be greater than or equals to 0!");
    
        final int[] ints = new int[length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = nextInt();
        }
        return ints;
    }
    
    /**
     * 生成一个随机数
     *
     * @return 随机数
     */
    public static float nextFloat() {
        return RANDOM.nextFloat();
    }
    
    /**
     * 生成一个随机数
     *
     * @param bound 上限
     * @return 随机数
     */
    public static float nextFloat(float bound) {
        Preconditions.argument(bound > 0, "bound can not be smaller than or equals to 0!");
        return RANDOM.nextFloat() % bound;
    }
    
    /**
     * 生成一些随机数
     *
     * @param length 长度
     * @param bound  上限
     * @return 随机数数组
     */
    public static float[] nextFloats(int length, float bound) {
        Preconditions.argument(length >= 0, "float length must be greater than or equals to 0!");
        Preconditions.argument(bound >= 0, "bound must be greater than or equals to 0!");
    
        final float[] floats = new float[length];
        for (int i = 0; i < floats.length; i++) {
            floats[i] = nextFloat(bound);
        }
        return floats;
    }
    
    /**
     * 生成一些随机数
     *
     * @param length 长度
     * @return 随机数数组
     */
    public static float[] nextFloats(int length) {
        Preconditions.argument(length >= 0, "float length must be greater than or equals to 0!");
    
        final float[] floats = new float[length];
        for (int i = 0; i < floats.length; i++) {
            floats[i] = nextFloat();
        }
        return floats;
    }
    
    /**
     * 生成一个随机数
     *
     * @return 随机数
     */
    public static double nextDouble() {
        return RANDOM.nextDouble();
    }
    
    /**
     * 生成一个随机数
     *
     * @param bound 上限
     * @return 随机数
     */
    public static double nextDouble(double bound) {
        Preconditions.argument(bound > 0, "bound can not be smaller than or equals to 0!");
        return RANDOM.nextDouble() % bound;
    }
    
    /**
     * 生成一些随机数
     *
     * @param length 长度
     * @param bound  上限
     * @return 随机数数组
     */
    public static double[] nextDoubles(int length, double bound) {
        Preconditions.argument(length >= 0, "double length must be greater than or equals to 0!");
        Preconditions.argument(bound >= 0, "bound must be greater than or equals to 0!");
    
        final double[] doubles = new double[length];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = nextDouble(bound);
        }
        return doubles;
    }
    
    /**
     * 生成一些随机数
     *
     * @param length 长度
     * @return 随机数数组
     */
    public static double[] nextDoubles(int length) {
        Preconditions.argument(length >= 0, "double length must be greater than or equals to 0!");
    
        final double[] doubles = new double[length];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = nextDouble();
        }
        return doubles;
    }
    
    /**
     * 生成一个高斯函数值
     *
     * @return 随机数
     */
    public static double nextGaussian() {
        return RANDOM.nextGaussian();
    }
    
    /**
     * 生成一个随机数
     *
     * @return 随机数
     */
    public static long nextLong() {
        return RANDOM.nextLong();
    }
    
    /**
     * 生成一个随机数
     *
     * @param bound 上限
     * @return 随机数
     */
    public static long nextLong(long bound) {
        Preconditions.argument(bound > 0, "bound can not be smaller than or equals to 0!");
        return RANDOM.nextLong() % bound;
    }
}