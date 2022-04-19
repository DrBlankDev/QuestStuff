package at.drblank.queststuff;

import at.drblank.queststuff.Commands.QuestCommand;
import at.drblank.queststuff.Listeners.QuestGuiListener;
import at.drblank.queststuff.Utils.MySql.MySql;
import at.drblank.queststuff.Utils.MySql.MySqlConfig;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class QuestStuff extends JavaPlugin {


    public static QuestStuff instance;
    private static MySql mySQL;

    public static MySql getMySQL() {
        return mySQL;
    }

    public static FileConfiguration cfg;

    public static QuestStuff getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        instance = this;

        // MySql load, MySql connect & Config load
        MySqlConfig file = new MySqlConfig();
        file.setStandard();
        file.readData();
        QuestStuff.cfg = this.getConfig();
        mySQL = new MySql();
        mySQL.connect();

        // Listener registration

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new QuestGuiListener(), this);

        // Command registration

        getCommand("quest").setExecutor(new QuestCommand());
        getCommand("quests").setExecutor(new QuestGuiListener());



    }

    @Override
    public void onDisable() {
        instance = this;

        // MySql disconnect
        mySQL.disconnect();

    }
}
