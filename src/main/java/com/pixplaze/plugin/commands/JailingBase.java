package com.pixplaze.plugin.commands;

import com.pixplaze.exceptions.PlayersNotFoundException;
import com.pixplaze.plugin.JailPlugin;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class JailingBase implements CommandExecutor {

    protected static final Logger logger = JailPlugin.getInstance().logger;
    protected static final Server server = JailPlugin.getInstance().getServer();
    // protected static List<Player> playersJailList;

    protected static ArrayList<Player> parseJailingPlayers(String[] playerNames) throws PlayersNotFoundException {
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

    protected abstract boolean jailAction(Player player, CommandSender sender);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<Player> jailingPlayers;
        try {
            jailingPlayers = parseJailingPlayers(args);
        } catch (PlayersNotFoundException e) {
            jailingPlayers = e.getFoundedPlayers();
            sender.sendMessage(e.getMessage());
        }
        var success = false;
        for (var player: jailingPlayers) {
            success = jailAction(player, sender);
        } return success;
    }
}
