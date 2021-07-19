package cn.chuanwise.toolkit.map.getter;

import lombok.Data;

@Data
public class SimplePathGetterConfiguration implements PathGetterConfiguration {
    boolean returnNullIfFail = false;
}
