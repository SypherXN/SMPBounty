package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PDCUtil;
import com.sypherxn.smpbounty.util.PlayerUtil;
import org.bukkit.entity.Player;

public class GetCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        if(!p.isOp()) {

            ChatUtil.sendMessage(p, "You do not have permission to use this command");
            return;

        }

        if(args.length < 1) {

            ChatUtil.sendMessage(p, "You need to specify your target");
            return;

        }

        String targetName = args[1];
        Player target = PlayerUtil.getPlayer(p, targetName);

        ChatUtil.sendMessage(p, target.getName() + " Bounty Data");
        ChatUtil.sendMessage(p, "EnableState: " + PDCUtil.getEnableState(target));
        ChatUtil.sendMessage(p, "Hunting: " + PlayerUtil.getPlayer(PDCUtil.getHunting(target)).getName());
        ChatUtil.sendMessage(p, "Targeting: " + PlayerUtil.getPlayer(PDCUtil.getTargeting(target)).getName());
        ChatUtil.sendMessage(p, "BountyHunter: " + PlayerUtil.getPlayer(PDCUtil.getBountyHunter(target)).getName());
        ChatUtil.sendMessage(p, "BountyPlacer: " + PlayerUtil.getPlayer(PDCUtil.getBountyPlacer(target)).getName());
        ChatUtil.sendMessage(p, "PlaceCooldown: " + PDCUtil.getRemainingPlaceCooldown(target));
        ChatUtil.sendMessage(p, "ShieldTime: " + PDCUtil.getRemainingShieldTime(target));
        ChatUtil.sendMessage(p, "RewardItems: " + PDCUtil.getRewardItems(target).toString());
        ChatUtil.sendMessage(p, "CollectItems: " + PDCUtil.getCollectItems(target).toString());


    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
