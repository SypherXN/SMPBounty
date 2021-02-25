package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.gui.GUI;
import com.sypherxn.smpbounty.util.PDCUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CollectCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        if(!PDCUtil.isEnabled(p)) {

            p.sendMessage("You must be bounty-enabled to collect items");
            return;

        }

        if(PDCUtil.getCollectItems(p).isEmpty()) {

            p.sendMessage("You have no items in your collection bin!");
            return;

        }

        Inventory bountyCollect = GUI.getCollectView(p);
        p.openInventory(bountyCollect);

    }

    @Override
    public String name() { return SMPBounty.getPlugin().commandManager.collect; }

    @Override
    public String info() { return "Open your bounty collection bin"; }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
