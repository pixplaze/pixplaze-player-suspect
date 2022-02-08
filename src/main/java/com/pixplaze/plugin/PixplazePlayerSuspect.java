package com.pixplaze.plugin;

import com.pixplaze.plugin.commands.Suspect;
import com.pixplaze.plugin.commands.Desuspect;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;
import java.util.logging.Logger;

public final class PixplazePlayerSuspect extends JavaPlugin {

    private static PixplazePlayerSuspect instance;
    public final Logger logger;
    public final String pluginName;

    public PixplazePlayerSuspect() {
        instance = this;
        this.logger = getLogger();
        this.pluginName = getName();
    }

    public static PixplazePlayerSuspect getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        createPluginFolder();
        Objects.requireNonNull(getCommand("suspect")).setExecutor(new Suspect());
        Objects.requireNonNull(getCommand("desuspect")).setExecutor(new Desuspect());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void createPluginFolder() {
        if (getDataFolder().mkdir()) {
            logger.info("%s folder does not exist, creating...".formatted(pluginName));
        }
    }
}
