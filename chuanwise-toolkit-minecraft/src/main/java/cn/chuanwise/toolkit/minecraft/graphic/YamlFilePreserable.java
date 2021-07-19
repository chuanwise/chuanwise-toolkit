package cn.chuanwise.toolkit.minecraft.graphic;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
public class YamlFilePreserable {
    File file;
    YamlConfiguration body;

    public YamlFilePreserable(File file) {
        this(file, new YamlConfiguration());
    }
}
