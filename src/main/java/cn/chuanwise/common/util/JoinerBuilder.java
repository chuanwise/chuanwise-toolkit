package cn.chuanwise.common.util;

/**
 * Joiner 的构造器
 *
 * @see Joiner
 * @author Chuanwise
 */
public class JoinerBuilder {
    
    protected String prefix = "";
    
    protected String suffix = "";
    
    protected String delimiter = ", ";
    
    protected String emptyString = "";
    
    protected boolean nullable;
    
    JoinerBuilder() {}
    
    public JoinerBuilder prefix(String prefix) {
        Preconditions.objectNonNull(prefix, "prefix");
        
        this.prefix = prefix;
        
        return this;
    }
    
    public JoinerBuilder suffix(String suffix) {
        Preconditions.objectNonNull(suffix, "suffix");
        
        this.suffix = suffix;
        
        return this;
    }
    
    public JoinerBuilder delimiter(String delimiter) {
        Preconditions.objectNonNull(delimiter, "delimiter");
        
        this.delimiter = delimiter;
        
        return this;
    }
    
    public JoinerBuilder emptyString(String emptyString) {
        Preconditions.objectNonNull(emptyString, "empty string");
        
        this.emptyString = emptyString;
        
        return this;
    }
    
    public JoinerBuilder nullable() {
        this.nullable = true;
        
        return this;
    }
    
    public JoinerBuilder nonNull() {
        this.nullable = false;
        
        return this;
    }
    
    public Joiner build() {
        return new Joiner(
            prefix,
            suffix,
            delimiter,
            emptyString,
            nullable
        );
    }
}
