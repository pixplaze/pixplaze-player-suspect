package com.pixplaze.plugin;

import com.pixplaze.plugin.commands.Suspect;
import com.pixplaze.plugin.commands.Desuspect;
import com.pixplaze.plugin.suspicion.SuspectSession;
import com.pixplaze.plugin.suspicion.Suspector;
import com.pixplaze.plugin.suspicion.SuspicionExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public final class PixplazePlayerSuspect extends JavaPlugin {

    private static PixplazePlayerSuspect instance;

    public final Logger logger;

    public PixplazePlayerSuspect() {
        instance = this;
        this.logger = getLogger();
    }

    public static PixplazePlayerSuspect getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("suspect")).setExecutor(new Suspect());
        Objects.requireNonNull(getCommand("desuspect")).setExecutor(new Desuspect());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public List<Suspector> loadSuspectedPlayers() {
        return null;
    }

    public void saveSuspectedPlayers() {

    }
}
