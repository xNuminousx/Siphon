package me.numin.siphon;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private ConfigFile configFile;
    private static Siphon plugin;

    public Config() {
        configFile = new ConfigFile("config");
        plugin = Siphon.getPlugin();
        loadConfig();
    }

    private FileConfiguration getConfig() {
        return configFile.getConfig();
    }

    private void loadConfig() {
        FileConfiguration config = plugin.getConfig();

        List<String> worlds = new ArrayList<>();
        worlds.add("world");
        worlds.add("world_nether");
        worlds.add("world_the_end");
        config.addDefault("EnabledWorlds", worlds);

        config.addDefault("HealthRegenerationAmount.OnEntityKill", 2);
        config.addDefault("HealthRegenerationAmount.OnPlayerKill", 4);
        config.addDefault("SendHealMessage", true);

        config.options().copyDefaults(true);
        plugin.saveConfig();
    }

    public static List<String> getEnabledWorlds() { return plugin.getConfig().getStringList("EnabledWorlds"); }
    public static boolean canSendHealMessage() { return plugin.getConfig().getBoolean("SendHealMessage"); }
    public static double getEntityKillAmount() { return plugin.getConfig().getDouble("HealthRegenerationAmount.OnEntityKill"); }
    public static double getPlayerKillAmount() { return plugin.getConfig().getDouble("HealthRegenerationAmount.OnPlayerKill"); }
}
