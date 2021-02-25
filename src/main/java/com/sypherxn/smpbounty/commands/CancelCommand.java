package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PDCUtil;
import com.sypherxn.smpbounty.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CancelCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        //Check to see if player can run this command
        if(!PDCUtil.hasTargeting(p)) {

            ChatUtil.sendMessage(p,"You have not placed a bounty yet");
            return;

        }

        UUID targetUUID = PDCUtil.getTargeting(p);
        Player target = PlayerUtil.getPlayer(targetUUID);

        if(PDCUtil.hasBountyHunter(target)) {

            ChatUtil.sendMessage(p, "You cannot cancel a bounty somebody has already accepted");
            return;

        }

        //Update information for the player running the command
        PDCUtil.clearTargeting(p);
        PDCUtil.setCollectItems(p, PDCUtil.getRewardItems(target));
        ChatUtil.sendMessage(p, "You have successfully removed the bounty off of " + target.getName());
        ChatUtil.sendMessage(p, "Your bounty has been placed into your /bounty collect");

        //Update information for the player being hunted
        PDCUtil.clearBountyPlacer(target);
        PDCUtil.clearRewardItems(target);
        ChatUtil.sendMessage(target, p.getName() + " has removed their bounty on you");

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
