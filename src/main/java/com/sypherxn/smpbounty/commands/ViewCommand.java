package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.gui.GUI;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PDCUtil;
import com.sypherxn.smpbounty.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class ViewCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        if(args.length < 2) {
            ChatUtil.sendMessage(p, "Correct usage /bounty view <player name>");
            return;
        }

        String targetName = args[1];
        Player target = PlayerUtil.getPlayer(p, targetName);

        if(target == null) { return; }

        Inventory bountyView = GUI.getRewardView(target);
        p.openInventory(bountyView);

    }

    @Override
    public String name() { return SMPBounty.getPlugin().commandManager.view; }

    @Override
    public String info() { return "View the reward of a player's bounty"; }

    @Override
    public String[] aliases() {
        String[] alias = {"v"};
        return alias;
    }
}
