package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.StatsUtil;
import org.bukkit.entity.Player;

public class StatsCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        ChatUtil.sendMessage(p, p.getName() + " Bounty Stats:");
        ChatUtil.sendMessage(p, "Bounty Kills: " + StatsUtil.getBountyKills(p));
        ChatUtil.sendMessage(p, "Bounties Failed: " + StatsUtil.getBountyFailed(p));
        ChatUtil.sendMessage(p, "Bounties Survived: " + StatsUtil.getBountySurvived(p));

    }

    @Override
    public String name() { return SMPBounty.getPlugin().commandManager.stats; }

    @Override
    public String info() { return "Shows your bounty stats"; }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
