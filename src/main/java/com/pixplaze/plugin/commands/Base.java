package com.pixplaze.plugin.commands;

import com.pixplaze.exceptions.PlayersNotFoundException;
import com.pixplaze.plugin.PixplazePlayerSuspect;
import com.pixplaze.plugin.suspicion.SuspectSession;
import com.pixplaze.plugin.suspicion.SuspicionExecutor;
import com.pixplaze.util.Players;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.logging.Logger;

public abstract class Base implements CommandExecutor {

    protected static final Logger logger = PixplazePlayerSuspect.getInstance().logger;
    protected static final Server server = PixplazePlayerSuspect.getInstance().getServer();
    protected static SuspicionExecutor executor;

    protected abstract boolean execute(Player player, CommandSender sender);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<Player> suspectingPlayers;
        try { // Проверяем валидность агрументов команды (правильность игровых имён)
            suspectingPlayers = Players.find(args, server);
        } catch (PlayersNotFoundException e) {
            suspectingPlayers = e.getFoundedPlayers();
            sender.sendMessage(e.getMessage());
        }
        // Устанавливаем реализацию подозрений и запускаем
        var success = false;
        executor = new SuspectSession();
        for (var player: suspectingPlayers) {
            success = execute(player, sender);
        } return success;
    }
}
