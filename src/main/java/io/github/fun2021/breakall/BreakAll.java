package io.github.fun2021.breakall;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BreakAll extends JavaPlugin {

    BreakAllAPI api;

    @Override
    public void onEnable() {
        api = new BreakAllAPI();
        new BreakAllListener();
        println("Plugin is Enabled");
    }

    @Override
    public void onDisable() {
        println("Plugin is Disabled");
    }

    public static BreakAll getInstance() {
        return (BreakAll) Bukkit.getPluginManager().getPlugin("BreakAll");
    }

    public void println(String message) {
        getLogger().info(message);
    }

    public BreakAllAPI getAPI() {
        return api;
    }

}
