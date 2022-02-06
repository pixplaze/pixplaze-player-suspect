package com.pixplaze.plugin.commands;

import com.pixplaze.plugin.PixplazePlayerSuspect;
import com.pixplaze.plugin.suspicion.SuspectSession;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Arrays;

public class DesuspectCommand extends CommandBase {

    @Override
    public boolean suspectAction(Player player, CommandSender sender) {
        var message = "";

        SuspectSession.desuspectPlayer(player);

        message = "The %s player is no longer suspect! Inventory restored. Game mode returned to survival".formatted(player.getName());
        logger.warning("Items before suspecting: " + Arrays.toString(PixplazePlayerSuspect.playerItems));
        logger.info(message);
        sender.sendMessage(message);
        return true;
    }
}
