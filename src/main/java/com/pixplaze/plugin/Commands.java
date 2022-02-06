package com.pixplaze.plugin;

import com.pixplaze.exceptions.PlayersNotFoundException;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.logging.Logger;

public final class Commands {

    private static final Logger logger = JailPlugin.getInstance().logger;
    private static final Server server = JailPlugin.getInstance().getServer();
    private static List<Player> playersJailList;

    private static ArrayList<Player> parseJailingPlayers(String[] playerNames) throws PlayersNotFoundException {
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

    static class JailCommand implements CommandExecutor {

        private static boolean jailPlayer(Player player, CommandSender sender) {
            var message = "";

            var inventory = player.getInventory();
            var itemStack = inventory.getContents();

            // Jailing player
            player.setGameMode(GameMode.ADVENTURE);
            JailPlugin.playerItems = Arrays.copyOf(itemStack, itemStack.length);
            inventory.clear();

            message = "Player %s was jailed! Inventory cleared. Game mode set to adventure".formatted(player.getName());
            logger.warning("Items after jailing: " + Arrays.toString(JailPlugin.playerItems));
            logger.info(message);
            sender.sendMessage(message);
            return true;
        }

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
                success = jailPlayer(player, sender);
            } return success;
        }
    }

    static class UnjailCommand implements CommandExecutor {

        @Override
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
            var playerName = args[0];
            var player = JailPlugin.getInstance().getServer().getPlayer(playerName);
            var message = "";

            if (player == null) {
                message = "No such player: %s!".formatted(playerName);
                sender.sendMessage(message);
                logger.warning(message);
                return false;
            } else if (JailPlugin.playerItems == null) {
                message = "Player %s was not jailed!".formatted(playerName);
                sender.sendMessage(message);
                logger.warning(message);
                return false;
            }

            player.setGameMode(GameMode.SURVIVAL);
            player.getInventory().setContents(JailPlugin.playerItems);
            logger.warning("Items before unjailing: " + Arrays.toString(JailPlugin.playerItems));

            message = "Player %s was unjailed! Inventory restored. Game mode returned to survival".formatted(playerName);
            logger.info(message);
            sender.sendMessage(message);

            return true;
        }
    }
}
