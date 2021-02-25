package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.PDCUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CancelCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        if(!PDCUtil.hasTargeting(p)) {

            p.sendMessage("You have not placed a bounty yet");
            return;

        }

        UUID targetUUID = PDCUtil.getTargeting(p);
        Player target = Bukkit.getPlayer(targetUUID);

        if(PDCUtil.hasBountyHunter(target)) {

            p.sendMessage("You cannot cancel a bounty somebody has already accepted");
            return;

        }

        PDCUtil.clearTargeting(p);
        PDCUtil.setCollectItems(p, PDCUtil.getRewardItems(target));
        p.sendMessage("You have successfully removed the bounty off of " + target.getName());
        p.sendMessage("Your bounty has been placed into your /bounty collect");

        PDCUtil.clearBountyPlacer(target);
        PDCUtil.clearRewardItems(target);
        target.sendMessage(p.getName() + " has removed their bounty on you");

    }

    @Override
    public String name() { return SMPBounty.getPlugin().commandManager.cancel; }

    @Override
    public String info() { return "Cancel a bounty you have put out"; }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
