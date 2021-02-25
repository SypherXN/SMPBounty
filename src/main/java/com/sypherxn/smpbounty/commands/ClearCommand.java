package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PDCUtil;
import org.bukkit.entity.Player;

public class ClearCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        if(!p.isOp()) {

            ChatUtil.sendMessage(p, "You do not have permission to use this command");
            return;

        }

        PDCUtil.clearPlaceTime(p);
        PDCUtil.clearShieldTime(p);
        PDCUtil.clearBountyPlacer(p);
        PDCUtil.clearRewardItems(p);
        PDCUtil.clearTargeting(p);
        PDCUtil.clearHunting(p);
        PDCUtil.clearCollectItems(p);
        PDCUtil.clearBountyHunter(p);

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
