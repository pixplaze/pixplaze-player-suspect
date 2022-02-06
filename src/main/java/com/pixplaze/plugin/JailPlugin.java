package com.pixplaze.plugin;

import com.pixplaze.plugin.commands.JailCommand;
import com.pixplaze.plugin.commands.UnjailCommand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
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
        Objects.requireNonNull(getCommand("jail")).setExecutor(new JailCommand());
        Objects.requireNonNull(getCommand("unjail")).setExecutor(new UnjailCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
