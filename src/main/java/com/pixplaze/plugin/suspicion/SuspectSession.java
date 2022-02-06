package com.pixplaze.plugin.suspicion;

import com.pixplaze.exceptions.PlayersNotFoundException;
import com.pixplaze.plugin.PixplazePlayerSuspect;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class SuspectSession {

    protected static final Logger logger = PixplazePlayerSuspect.getInstance().logger;
    protected static final Server server = PixplazePlayerSuspect.getInstance().getServer();

    private List<Suspector> suspectors = null;

    public static ArrayList<Player> parseSuspectingPlayers(String[] playerNames) throws PlayersNotFoundException {
        var foundedPlayers = new ArrayList<Player>();
        var notFoundedNames = new ArrayList<String>();
        for (var name: playerNames) {
            var player = server.getPlayer(name);
            if (player != null) {
                foundedPlayers.add(player);
            } else {
                notFoundedNames.add(name);
            }
        }
        if (foundedPlayers.isEmpty() || notFoundedNames.size() != 0) {
            throw new PlayersNotFoundException(foundedPlayers, notFoundedNames.toArray(String[]::new));
        } return foundedPlayers;
    }

    @Deprecated
    public static void suspectPlayer(Player player) {
        var inventory = player.getInventory();
        var itemStack = inventory.getContents();

        // Jailing player
        player.setGameMode(GameMode.ADVENTURE);
        PixplazePlayerSuspect.playerItems = Arrays.copyOf(itemStack, itemStack.length);
        inventory.clear();
    }

    @Deprecated
    public static void desuspectPlayer(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().setContents(PixplazePlayerSuspect.playerItems);
    }
}
