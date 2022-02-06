package com.pixplaze.plugin.commands;

import com.pixplaze.plugin.JailPlugin;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class JailCommand extends JailingBase {

    @Override
    public boolean jailAction(Player player, CommandSender sender) {
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
}
