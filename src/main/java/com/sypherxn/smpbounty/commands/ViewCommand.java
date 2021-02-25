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

        if(!PDCUtil.isEnabled(p)) {

            ChatUtil.sendMessage(p, "You cannot view bounties if you are not bounty-enabled");
            return;

        }

        if(args.length < 1) {

            ChatUtil.sendMessage(p, "Correct usage /bounty accept <player name>");

        }

        String targetName = args[1];
        Player target = PlayerUtil.getPlayer(p, targetName);
        UUID targetUUID = target.getUniqueId();

        if(targetUUID == null) {

            ChatUtil.sendMessage(p, targetName + " could not be found");
            return;

        }

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
