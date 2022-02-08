package com.pixplaze.plugin.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Arrays;

public class Desuspect extends Base {

    @Override
    public boolean execute(Player player, CommandSender sender) {
        var message = "";

        Base.executor.desuspectPlayer(player);

        message = "The %s player is no longer suspect! Inventory restored. Game mode returned to survival".formatted(player.getName());
        logger.warning("Items before suspecting: " + Arrays.toString(Arrays.stream(player.getInventory().getContents()).toArray()));
        logger.info(message);
        sender.sendMessage(message);
        return true;
    }
}
