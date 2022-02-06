package com.pixplaze.exceptions;

import org.bukkit.entity.Player;

import java.util.List;

public class PlayersNotFoundException extends RuntimeException {

    private final String[] playerNames;
    private final List<Player> players;

    public PlayersNotFoundException(List<Player> foundedPlayers, String[] notFoundedNames) {
        super("No such players: %s".formatted(
                String.join(", ", notFoundedNames)
        ));
        this.players = foundedPlayers;
        this.playerNames = notFoundedNames;
    }

    public String[] getNotFoundedNames() {
        return this.playerNames;
    }

    public List<Player> getFoundedPlayers() {
        return this.players;
    }
}
