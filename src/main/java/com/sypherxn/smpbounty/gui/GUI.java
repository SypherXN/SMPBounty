package com.sypherxn.smpbounty.gui;

import com.sypherxn.smpbounty.util.PDCUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GUI {

    public static Inventory getRewardView(Player p) {

        Inventory inv = Bukkit.createInventory(null, 9, "Bounty View: " + p.getName());
        ArrayList<ItemStack> items = PDCUtil.getRewardItems(p);

        items.stream()
                .forEach(inv::addItem);

        return inv;

    }

    public static Inventory getCollectView(Player p) {

        Inventory inv = Bukkit.createInventory(null, 9, "Bounty Collect: " + p.getName());
        ArrayList<ItemStack> items = PDCUtil.getCollectItems(p);

        items.stream()
                .forEach(inv::addItem);

        return inv;

    }

    public static Inventory getListView() {

        ArrayList<Player> onlinePlayerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        Inventory inv = Bukkit.createInventory(null, 54, "Bounty List");

        for(int i = 0; i < onlinePlayerList.size(); i++) {

            if(PDCUtil.hasBountyPlacer(onlinePlayerList.get(i)) && !PDCUtil.hasBountyHunter(onlinePlayerList.get(i))) {

                ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD, 1);

                ArrayList<String> desc = new ArrayList<>();
                SkullMeta meta = (SkullMeta) playerSkull.getItemMeta();
                meta.setOwningPlayer(onlinePlayerList.get(i));
                meta.setDisplayName(onlinePlayerList.get(i).getName());
                desc.add("Click to view bounty");
                desc.add("Bounty Placer: " + Bukkit.getPlayer(PDCUtil.getBountyPlacer(onlinePlayerList.get(i))).getName());
                meta.setLore(desc);

                playerSkull.setItemMeta(meta);

                inv.addItem(playerSkull);

            }

        }

        return inv;

    }

}
