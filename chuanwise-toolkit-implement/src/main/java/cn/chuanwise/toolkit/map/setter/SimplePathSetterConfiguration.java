package cn.chuanwise.toolkit.map.setter;

import lombok.Data;

@Data
public class SimplePathSetterConfiguration implements PathSetterConfiguration {
    boolean createPathIfNoSuchPath = true;
}
