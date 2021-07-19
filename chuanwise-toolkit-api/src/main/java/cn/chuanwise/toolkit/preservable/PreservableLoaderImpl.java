package cn.chuanwise.toolkit.preservable;

import cn.chuanwise.toolkit.serialize.serializer.Serializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class PreservableLoaderImpl<M> implements PreservableLoader<M> {
    Serializer defaultSerializer;
}
