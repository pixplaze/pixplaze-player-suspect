package com.pixplaze.plugin.commands;

import com.pixplaze.plugin.PixplazePlayerSuspect;
import com.pixplaze.plugin.suspicion.SuspectSession;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SuspectCommand extends CommandBase {

    @Override
    public boolean suspectAction(Player player, CommandSender sender) {
        var message = "";

        SuspectSession.suspectPlayer(player);

        message = "The %s player was suspected! Inventory cleared. Game mode set to adventure".formatted(player.getName());
        logger.warning("Items after suspecting: " + Arrays.toString(PixplazePlayerSuspect.playerItems));
        logger.info(message);
        sender.sendMessage(message);
        return true;
    }
}
