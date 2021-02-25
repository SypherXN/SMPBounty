package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.gui.GUI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ListCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        Inventory bountyList = GUI.getListView();

        p.openInventory(bountyList);

    }

    @Override
    public String name() { return SMPBounty.getPlugin().commandManager.list; }

    @Override
    public String info() { return "Shows all online players with bounties on them"; }

    @Override
    public String[] aliases() {
        String[] alias = {"l"};
        return alias;
    }
}
