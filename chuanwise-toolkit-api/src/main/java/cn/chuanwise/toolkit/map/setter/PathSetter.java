package cn.chuanwise.toolkit.map.setter;

public interface PathSetter {
    void set(String path, Object object);

    Object remove(String path);

    PathSetterConfiguration getPathSetterConfiguration();

    void setPathSetterConfiguration(PathSetterConfiguration configuration);
}
