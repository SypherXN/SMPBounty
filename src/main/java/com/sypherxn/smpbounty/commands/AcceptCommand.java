package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PDCUtil;
import com.sypherxn.smpbounty.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AcceptCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        //Checks for players ability to run the command
        if(!PDCUtil.isEnabled(p)) {

            ChatUtil.sendMessage(p, "You must be bounty-enabled to accept bounties");
            return;

        }

        if(PDCUtil.hasHunting(p)) {

            ChatUtil.sendMessage(p, "You can only hunt one player at a time");
            return;

        }

        if(PDCUtil.hasTargeting(p)) {

            ChatUtil.sendMessage(p, "You cannot accept a bounty while you have one placed on someone");
            return;

        }

        if(!PDCUtil.getCollectItems(p).isEmpty()) {

            ChatUtil.sendMessage(p, "You cannot accept a bounty while there items in your collection");
            return;

        }

        if(args.length < 1) {

            ChatUtil.sendMessage(p, "Correct usage /bounty accept <player name>");

        }

        String targetName = args[1];
        Player target = PlayerUtil.getPlayer(targetName);
        UUID targetUUID = target.getUniqueId();

        if(targetUUID == null) {

            ChatUtil.sendMessage(p, targetName + " could not be found");
            return;

        }

        if(target.getUniqueId().equals(p.getUniqueId())) {

            ChatUtil.sendMessage(p, "You cannot accept a bounty on yourself");
            return;

        }

        targetName = target.getName();
        if(!PDCUtil.hasBountyPlacer(target)) {

            ChatUtil.sendMessage(p, targetName + " doesn't have a bounty on them");
            return;

        }

        if(PDCUtil.hasHunting(target)) {

            ChatUtil.sendMessage(p, targetName + " is already being hunted by " + PlayerUtil.getPlayer(PDCUtil.getBountyHunter(target)).getName());
            return;

        }

        //Retrieves information about players related to this command
        UUID bountyPlacerUUID = PDCUtil.getBountyPlacer(target);
        Player bountyPlacer = PlayerUtil.getPlayer(bountyPlacerUUID);
        String bountyPlacerName = bountyPlacer.getName();

        //Sets information for the player being targeted
        PDCUtil.setBountyHunter(target, p.getUniqueId());
        ChatUtil.sendMessage(target, p.getName() + " has accepted " + bountyPlacerName + "'s bounty on you");

        //Sets information for the player accepting
        PDCUtil.setHunting(p, targetUUID);
        ChatUtil.sendMessage(p, "You have accepted " + bountyPlacerName + "'s bounty on " + targetName);

    }

    @Override
    public String name() { return SMPBounty.getPlugin().commandManager.accept; }

    @Override
    public String info() { return "Accept a bounty on a player to start hunting them"; }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
