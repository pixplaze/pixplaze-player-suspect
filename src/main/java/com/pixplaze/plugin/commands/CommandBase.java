package com.pixplaze.plugin.commands;

import com.pixplaze.exceptions.PlayersNotFoundException;
import com.pixplaze.plugin.PixplazePlayerSuspect;
import com.pixplaze.plugin.suspicion.SuspectSession;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.logging.Logger;

public abstract class CommandBase implements CommandExecutor {

    protected static final Logger logger = PixplazePlayerSuspect.getInstance().logger;
    protected static final Server server = PixplazePlayerSuspect.getInstance().getServer();

    protected abstract boolean suspectAction(Player player, CommandSender sender);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<Player> suspectingPlayers;
        try {
            suspectingPlayers = SuspectSession.parseSuspectingPlayers(args);
        } catch (PlayersNotFoundException e) {
            suspectingPlayers = e.getFoundedPlayers();
            sender.sendMessage(e.getMessage());
        }
        var success = false;
        for (var player: suspectingPlayers) {
            success = suspectAction(player, sender);
        } return success;
    }
}
