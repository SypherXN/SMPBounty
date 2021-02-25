package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.PDCUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AcceptCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        if(!PDCUtil.isEnabled(p)) {

            p.sendMessage("You must be bounty-enabled to accept bounties");
            return;

        }

        if(PDCUtil.hasHunting(p)) {

            p.sendMessage("You can only hunt one player at a time");
            return;

        }

        if(PDCUtil.hasTargeting(p)) {

            p.sendMessage("You cannot accept a bounty while you have one placed on someone");
            return;

        }

        if(!PDCUtil.getCollectItems(p).isEmpty()) {

            p.sendMessage("You cannot accept a bounty while there items in your collection");
            return;

        }

        String targetName = args[1];
        Player target = Bukkit.getPlayerExact(targetName);
        UUID targetUUID = target.getUniqueId();

        if(targetUUID == null) {

            p.sendMessage(targetName + " could not be found");
            return;

        }

        if(target.getUniqueId().equals(p.getUniqueId())) {

            p.sendMessage("You cannot accept a bounty on yourself");
            return;

        }

        targetName = target.getName();
        if(!PDCUtil.hasBountyPlacer(target)) {

            p.sendMessage(targetName + " doesn't have a bounty on them");
            return;

        }

        if(PDCUtil.hasHunting(target)) {

            p.sendMessage(targetName + " is already being hunted by " + Bukkit.getPlayer(PDCUtil.getBountyHunter(target)).getName());
            return;

        }

        UUID bountyPlacerUUID = PDCUtil.getBountyPlacer(target);
        Player bountyPlacer = Bukkit.getPlayer(bountyPlacerUUID);
        String bountyPlacerName = bountyPlacer.getName();

        PDCUtil.setBountyHunter(target, p.getUniqueId());
        target.sendMessage(p.getName() + " has accepted " + bountyPlacerName + "'s bounty on you");

        PDCUtil.setHunting(p, targetUUID);
        p.sendMessage("You have accepted " + bountyPlacerName + "'s bounty on " + targetName);

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
