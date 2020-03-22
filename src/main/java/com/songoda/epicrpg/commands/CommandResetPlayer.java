package com.songoda.epicrpg.commands;

import com.songoda.core.commands.AbstractCommand;
import com.songoda.epicrpg.EpicRPG;
import com.songoda.epicrpg.gui.GuiMain;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandResetPlayer extends AbstractCommand {

    private final EpicRPG plugin;

    public CommandResetPlayer(EpicRPG plugin) {
        super(CommandType.PLAYER_ONLY, "resetplayer");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);

        plugin.getPlayerManager().getPlayer(offlinePlayer).reset();
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "epicrpg.admin";
    }

    @Override
    public String getSyntax() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}