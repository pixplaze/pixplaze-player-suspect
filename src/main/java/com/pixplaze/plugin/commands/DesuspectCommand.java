package com.pixplaze.plugin.commands;

import com.pixplaze.plugin.PixplazePlayerSuspect;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Arrays;

public class DesuspectCommand extends SuspectBase {

    @Override
    public boolean suspectAction(Player player, CommandSender sender) {
        var message = "";

        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().setContents(PixplazePlayerSuspect.playerItems);
        logger.warning("Items before suspecting: " + Arrays.toString(PixplazePlayerSuspect.playerItems));

        message = "The %s player is no longer suspect! Inventory restored. Game mode returned to survival".formatted(player.getName());
        logger.info(message);
        sender.sendMessage(message);
        return true;
    }
}
