package com.pixplaze.plugin.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Suspect extends Base {

    @Override
    public boolean execute(Player player, CommandSender sender) {
        var message = "";

        Base.executor.suspectPlayer(player);
        Base.executor.saveSuspectors();

        message = "The %s player was suspected! Inventory cleared. Game mode set to adventure".formatted(player.getName());
        logger.warning("Items after suspecting: " + Arrays.toString(player.getInventory().getContents()));
        logger.info(message);
        sender.sendMessage(message);
        return true;
    }
}
