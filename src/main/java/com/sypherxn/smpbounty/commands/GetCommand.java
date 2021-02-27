package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
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

        if(args.length < 2) {

            ChatUtil.sendMessage(p, "Correct Usage: /bounty get <Player Name>");
            return;

        }

        Player target = PlayerUtil.getPlayer(p, args[1]);

        if(target == null) { return; }

        ChatUtil.sendMessage(p, target.getName() + " Bounty Data");
        ChatUtil.sendMessage(p, "EnableState: " + PDCUtil.getEnableState(target));
        ChatUtil.sendMessage(p, "Hunting: " + PDCUtil.getHunting(target).toString());
        ChatUtil.sendMessage(p, "Targeting: " + PDCUtil.getTargeting(target).toString());
        ChatUtil.sendMessage(p, "BountyHunter: " + PDCUtil.getBountyHunter(target).toString());
        ChatUtil.sendMessage(p, "BountyPlacer: " + PDCUtil.getBountyPlacer(target).toString());
        ChatUtil.sendMessage(p, "PlaceCooldown: " + PDCUtil.getRemainingPlaceCooldown(target));
        ChatUtil.sendMessage(p, "ShieldTime: " + PDCUtil.getRemainingShieldTime(target));
        ChatUtil.sendMessage(p, "CombatTag: " + PDCUtil.getRemainingCombatTag(target));
        ChatUtil.sendMessage(p, "RewardItems: " + PDCUtil.getRewardItems(target).toString());
        ChatUtil.sendMessage(p, "CollectItems: " + PDCUtil.getCollectItems(target).toString());


    }

    @Override
    public String name() { return SMPBounty.getPlugin().commandManager.get; }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
