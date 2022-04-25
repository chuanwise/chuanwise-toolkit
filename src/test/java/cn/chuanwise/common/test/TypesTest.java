package cn.chuanwise.common.test;

import cn.chuanwise.common.util.Types;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TypesTest {
    
    static class Grandparent {
    }
    
    static class Parent extends Grandparent {
    }
    
    static class Son extends Parent {
    }
    
    @Test
    void testDistance() {
        Assertions.assertEquals(0, Types.getTypeDistanceTo(Son.class, Son.class));
        Assertions.assertEquals(1, Types.getTypeDistanceTo(Son.class, Parent.class));
        Assertions.assertEquals(1, Types.getTypeDistanceTo(Parent.class, Grandparent.class));
        Assertions.assertEquals(2, Types.getTypeDistanceTo(Son.class, Grandparent.class));
    }
}
