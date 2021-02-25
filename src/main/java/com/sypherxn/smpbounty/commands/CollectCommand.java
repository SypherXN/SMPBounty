package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.gui.GUI;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PDCUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CollectCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        //Check if player is allowed to run the command
        if(!PDCUtil.isEnabled(p)) {

            ChatUtil.sendMessage(p, "You must be bounty-enabled to collect items");
            return;

        }

        if(PDCUtil.getCollectItems(p).isEmpty()) {

            ChatUtil.sendMessage(p, "You have no items in your collection bin!");
            return;

        }

        //Create inventory for the player to open
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
