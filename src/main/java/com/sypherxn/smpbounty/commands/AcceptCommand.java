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

        if(args.length < 2) {
            ChatUtil.sendMessage(p, "Correct usage /bounty accept <Player Name>");
            return;
        }

        String targetName = args[1];
        Player target = PlayerUtil.getPlayer(p, targetName);

        if(target == null) { return; }

        UUID targetUUID = target.getUniqueId();

        if(!PDCUtil.isEnabled(p)) {
            ChatUtil.sendMessage(p, "You must be bounty-enabled to accept bounties");
            return;
        } else if(PDCUtil.hasHunting(p)) {
            ChatUtil.sendMessage(p, "You can only hunt one player at a time");
            return;
        } else if(PDCUtil.hasTargeting(p)) {
            ChatUtil.sendMessage(p, "You cannot accept a bounty while you have one placed on someone");
            return;
        } else if(!PDCUtil.getCollectItems(p).isEmpty()) {
            ChatUtil.sendMessage(p, "You cannot accept a bounty while there are items in your collection");
            return;
        } else if(target.getUniqueId().equals(p.getUniqueId())) {
            ChatUtil.sendMessage(p, "You cannot accept a bounty on yourself");
            return;
        } else if(!PDCUtil.hasBountyPlacer(target)) {
            ChatUtil.sendMessage(p, target.getName() + " doesn't have a bounty on them");
            return;
        } else if(PDCUtil.hasHunting(target)) {
            ChatUtil.sendMessage(p, target.getName() + " is already being hunted by " + PlayerUtil.getPlayer(PDCUtil.getBountyHunter(target)).getName());
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
        ChatUtil.sendMessage(p, "You have accepted " + bountyPlacerName + "'s bounty on " + target.getName());

        ChatUtil.sendMessage(bountyPlacer, p.getName() + " has accepted your bounty on " + target.getName());

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
