package cn.chuanwise.common.test;

import cn.chuanwise.common.util.Objects;
import cn.chuanwise.common.util.Reflections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectsTest {
    
    @Test
    void testSafeCast() {
        Assertions.assertEquals(1, Objects.safeCast(1, int.class));
    }
    
    @Test
    @SuppressWarnings("all")
    void booleanTest() throws Exception {
        final String newStringName = "sunrise.lang.String";
        Reflections.setFieldValue(String.class, Class.class.getDeclaredField("name"), newStringName);
    
        System.out.println(Class.forName(newStringName));
    }
}
