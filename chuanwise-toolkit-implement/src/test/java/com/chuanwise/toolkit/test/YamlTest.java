package com.chuanwise.toolkit.test;

import com.chuanwise.toolkit.serialize.serializer.Serializer;
import com.chuanwise.toolkit.serialize.serializer.object.DeserializedObject;
import com.chuanwise.utility.del.YamlUtility;

public class YamlTest {
    static final String STRING = "menu-settings:\n" +
            "  name: '&1&lServer Menu &6&l(English)'\n" +
            "  rows: 3\n" +
            "  commands:\n" +
            "  - 'menu_en'\n" +
            "  auto-refresh: 5\n" +
            "  open-actions: []\n" +
            "  open-with-item:\n" +
            "    material: lead\n" +
            "    left-click: true\n" +
            "    right-click: true";

    public static void main(String[] args) {
        final Serializer serializer = YamlUtility.SERIALIZER;
        final DeserializedObject deserialize = serializer.deserialize(STRING);
        System.out.println(deserialize);
        System.out.println(deserialize.getString("menu-settings.open-with-item.material"));
    }
}
