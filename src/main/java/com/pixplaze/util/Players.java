package com.pixplaze.util;

import com.pixplaze.exceptions.PlayersNotFoundException;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.*;

public class Players {

    public static List<Player> find(String[] playerNames, Server server) throws PlayersNotFoundException {
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
        if (notFoundedNames.size() != 0) {
            throw new PlayersNotFoundException(foundedPlayers, notFoundedNames.toArray(String[]::new));
        } return foundedPlayers;
    }
}
