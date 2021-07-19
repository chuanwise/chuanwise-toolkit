package cn.chuanwise.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 实验性的 API
 * 拥有此注解的部件，仅供实验使用，不保证功能稳定。
 * @author Chuanwise
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Experimental {
}
