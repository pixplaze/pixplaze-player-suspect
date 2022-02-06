package com.pixplaze.plugin;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public final class JailPlugin extends JavaPlugin {

    private static JailPlugin instance;
    public final Logger logger;
    public static ItemStack[] playerItems;

    public JailPlugin() {
        instance = this;
        logger = getLogger();
    }

    public static JailPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("jail").setExecutor(new Commands.JailCommand());
        getCommand("unjail").setExecutor(new Commands.UnjailCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
