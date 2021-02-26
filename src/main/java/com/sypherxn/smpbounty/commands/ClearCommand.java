package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PDCUtil;
import com.sypherxn.smpbounty.util.PlayerUtil;
import org.bukkit.entity.Player;

public class ClearCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        if(!p.isOp()) {

            ChatUtil.sendMessage(p, "You do not have permission to use this command");
            return;

        }

        if(args.length < 2) {

            ChatUtil.sendMessage(p, "Correct Usage: /bounty clear <Player Name>");
            return;

        }

        Player target = PlayerUtil.getPlayer(p, args[1]);

        if(target == null) { return; }

        PDCUtil.clearPlaceTime(target);
        PDCUtil.clearShieldTime(target);
        PDCUtil.clearBountyPlacer(target);
        PDCUtil.clearRewardItems(target);
        PDCUtil.clearTargeting(target);
        PDCUtil.clearHunting(target);
        PDCUtil.clearCollectItems(target);
        PDCUtil.clearBountyHunter(target);

        ChatUtil.sendMessage(p, "You have successfully cleared " + target.getName() + "'s bounty data");

    }

    @Override
    public String name() { return SMPBounty.getPlugin().commandManager.clear; }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
