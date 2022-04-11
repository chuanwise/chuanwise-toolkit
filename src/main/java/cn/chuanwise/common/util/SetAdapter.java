package cn.chuanwise.common.util;

import java.util.Set;

/**
 * 集合适配器
 *
 * @author Chuanwise
 */
public class SetAdapter<E>
    extends CollectionAdapter<E>
    implements Set<E> {
    
    public SetAdapter(Set<E> set) {
        super(set);
    }
}
