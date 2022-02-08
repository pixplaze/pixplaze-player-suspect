package com.pixplaze.plugin.suspicion;

import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface SuspicionExecutor {
    void suspectPlayer(Player player);
    void desuspectPlayer(Player player);
    Set<Suspector> loadSuspectors() throws IOException;
    void saveSuspectors();
}
