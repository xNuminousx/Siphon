package me.numin.siphon;

import org.bukkit.plugin.java.JavaPlugin;

public final class Siphon extends JavaPlugin {

    private static Siphon plugin;

    @Override
    public void onEnable() {
        plugin = this;
        new Config();
        getServer().getPluginManager().registerEvents(new SiphonListener(), plugin);
        getLogger().info("Successfully enabled " + getName());
    }

    @Override
    public void onDisable() {
        getLogger().info("Successfully disabled " + getName());
    }

    public static Siphon getPlugin() {
        return plugin;
    }
}
