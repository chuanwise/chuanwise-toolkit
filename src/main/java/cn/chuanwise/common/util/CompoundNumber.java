package cn.chuanwise.common.util;

import java.util.Objects;

/**
 * 复合数字，用于数字之间的比较
 */
public final class CompoundNumber
    extends Number {
    
    /**
     * 真实数字
     */
    private final Number number;
    
    public CompoundNumber(Number number) {
        Preconditions.objectNonNull(number, "number");
        
        this.number = number;
    }
    
    @Override
    public int intValue() {
        return number.intValue();
    }
    
    @Override
    public long longValue() {
        return number.longValue();
    }
    
    @Override
    public float floatValue() {
        return number.floatValue();
    }
    
    @Override
    public double doubleValue() {
        return number.doubleValue();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        CompoundNumber that = (CompoundNumber) o;
        return Objects.equals(number, that.number);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
