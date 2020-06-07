package com.songoda.epicrpg.commands;

import com.songoda.core.commands.AbstractCommand;
import com.songoda.epicrpg.EpicRPG;
import com.songoda.epicrpg.story.contender.StoryContender;
import com.songoda.epicrpg.story.contender.StoryParty;
import com.songoda.epicrpg.story.contender.StoryPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandPartyLeave extends AbstractCommand {

    private final EpicRPG plugin;

    public CommandPartyLeave(EpicRPG plugin) {
        super(CommandType.PLAYER_ONLY, "party leave");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        Player player = (Player) sender;

        StoryContender contender = plugin.getContendentManager().getContender(player);

        if (contender instanceof StoryPlayer) {
            plugin.getLocale().newMessage("&cYou are not in a party...").sendPrefixedMessage(player);
            return ReturnType.FAILURE;
        }

        StoryParty storyParty = (StoryParty) contender;
        StoryPlayer storyPlayer = plugin.getContendentManager().getPlayer(player);

        if (storyParty.isLeader(storyPlayer)) {
            plugin.getLocale().newMessage("&cYou are the leader of this party...").sendPrefixedMessage(player);
            return ReturnType.FAILURE;
        }

        plugin.getQuestTask().remove(storyParty, player);
        storyPlayer.setParty(null);
        storyParty.removePlayer(storyPlayer);

        plugin.getLocale().newMessage("&aYou left the party successfully!").sendPrefixedMessage(player);
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "epicrpg.party.disband";
    }

    @Override
    public String getSyntax() {
        return "party disband";
    }

    @Override
    public String getDescription() {
        return "Create a disband.";
    }
}