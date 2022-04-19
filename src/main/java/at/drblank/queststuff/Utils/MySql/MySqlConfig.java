package at.drblank.queststuff.Utils.MySql;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MySqlConfig {

    public void setStandard() {
        FileConfiguration cfg = getFileConfig();

        cfg.options().copyDefaults(true);

        cfg.addDefault("host", "host");
        cfg.addDefault("port", "3306");
        cfg.addDefault("database", "database");
        cfg.addDefault("username", "username");
        cfg.addDefault("password", "password");

        try {
            cfg.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFile() {
        return new File("plugins/SpigotStuffLobby", "MySQL.yml");
    }

    private FileConfiguration getFileConfig() {
        return YamlConfiguration.loadConfiguration(getFile());
    }

    public void readData() {
    FileConfiguration cfg = getFileConfig();
        MySql.host = cfg.getString("host");
        MySql.port = cfg.getString("port");
        MySql.database = cfg.getString("database");
        MySql.username = cfg.getString("username");
        MySql.password = cfg.getString("password");

    }

}
