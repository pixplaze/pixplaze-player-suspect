package com.pixplaze.plugin;

import com.pixplaze.plugin.commands.SuspectCommand;
import com.pixplaze.plugin.commands.DesuspectCommand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class PixplazePlayerSuspect extends JavaPlugin {

    private static PixplazePlayerSuspect instance;
    public final Logger logger;
    public static ItemStack[] playerItems;

    public PixplazePlayerSuspect() {
        instance = this;
        logger = getLogger();
    }

    public static PixplazePlayerSuspect getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("jail")).setExecutor(new SuspectCommand());
        Objects.requireNonNull(getCommand("unjail")).setExecutor(new DesuspectCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
