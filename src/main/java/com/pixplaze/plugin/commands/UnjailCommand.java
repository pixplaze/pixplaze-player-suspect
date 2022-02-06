package com.pixplaze.plugin.commands;

import com.pixplaze.plugin.JailPlugin;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Arrays;

public class UnjailCommand extends JailingBase {

    @Override
    public boolean jailAction(Player player, CommandSender sender) {
        var message = "";

        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().setContents(JailPlugin.playerItems);
        logger.warning("Items before unjailing: " + Arrays.toString(JailPlugin.playerItems));

        message = "Player %s was unjailed! Inventory restored. Game mode returned to survival".formatted(player.getName());
        logger.info(message);
        sender.sendMessage(message);
        return true;
    }
}
