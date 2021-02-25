package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.util.PDCUtil;
import org.bukkit.entity.Player;

public class HelpCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

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
    public String name() {
        return "help";
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String[] aliases() {
        String[] alias = {"?"};
        return alias;
    }

}
