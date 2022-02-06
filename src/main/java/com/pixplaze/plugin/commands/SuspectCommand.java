package com.pixplaze.plugin.commands;

import com.pixplaze.plugin.PixplazePlayerSuspect;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SuspectCommand extends SuspectBase {

    @Override
    public boolean suspectAction(Player player, CommandSender sender) {
        var message = "";

        var inventory = player.getInventory();
        var itemStack = inventory.getContents();

        // Jailing player
        player.setGameMode(GameMode.ADVENTURE);
        PixplazePlayerSuspect.playerItems = Arrays.copyOf(itemStack, itemStack.length);
        inventory.clear();

        message = "The %s player was suspected! Inventory cleared. Game mode set to adventure".formatted(player.getName());
        logger.warning("Items after suspecting: " + Arrays.toString(PixplazePlayerSuspect.playerItems));
        logger.info(message);
        sender.sendMessage(message);
        return true;
    }
}
