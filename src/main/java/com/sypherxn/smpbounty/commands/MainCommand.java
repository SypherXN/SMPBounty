package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.gui.GUI;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PDCUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MainCommand extends SubCommand{

    @Override
    public void onCommand(Player p, String[] args){
        Inventory inv = GUI.getMainView(p);
        
        p.openInventory(inv);
    }

    @Override
    public String name(){ return SMPBounty.getPlugin().commandManager.bounty; }

    @Override
    public String info() { return "GUI of all options"; }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
