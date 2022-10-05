package cn.chuanwise.common.test;

import cn.chuanwise.common.util.Types;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TypesTest {
    
    static class StringList
        extends ArrayList<String> {
    }
    
    static class MiraiList
        extends StringList {
    }
    
    @Test
    void testTypeParameter() {
        System.out.println(ArrayList.class.getTypeParameters()[0]);
    
        System.out.println(Types.getTypeParameterClass(MiraiList.class, List.class, "E"));
        System.out.println(Types.getTypeParameterClass(MiraiList.class, Collection.class, "E"));
    }
}
