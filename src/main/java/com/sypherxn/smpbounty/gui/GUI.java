package com.sypherxn.smpbounty.gui;

import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PDCUtil;
import com.sypherxn.smpbounty.util.PlayerUtil;
import com.sypherxn.smpbounty.util.StatsUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class GUI {

    public static Inventory getActiveRewardView(Player p) {

        Inventory inv = Bukkit.createInventory(null, 9, "Bounty View: " + p.getName());
        ArrayList<ItemStack> items = PDCUtil.getRewardItems(p);

        items.stream()
                .forEach(inv::addItem);

        return inv;

    }

    public static Inventory getRewardView(Player p) {

        Inventory inv = Bukkit.createInventory(null, 18, "Bounty View: " + p.getName());
        ArrayList<ItemStack> items = PDCUtil.getRewardItems(p);

        items.stream()
                .forEach(inv::addItem);

        ItemStack confirm = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta confirmMeta = confirm.getItemMeta();
        ArrayList<String> confirmDesc = new ArrayList<>();
        confirmDesc.add(ChatColor.GREEN + "Click to accept the bounty");
        confirmMeta.setLore(confirmDesc);
        confirmMeta.setDisplayName(ChatColor.GREEN + "CONFIRM");
        confirm.setItemMeta(confirmMeta);

        ItemStack cancel = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta cancelMeta = cancel.getItemMeta();
        ArrayList<String> cancelDesc = new ArrayList<>();
        cancelDesc.add(ChatColor.RED + "Click to go back");
        cancelMeta.setLore(cancelDesc);
        cancelMeta.setDisplayName(ChatColor.RED + "CANCEL");
        cancel.setItemMeta(cancelMeta);

        inv.addItem(confirm);
        inv.addItem(cancel);

        return inv;

    }

    public static Inventory getCollectView(Player p) {

        Inventory inv = Bukkit.createInventory(null, 9, "Bounty Collect: " + p.getName());
        ArrayList<ItemStack> items = PDCUtil.getCollectItems(p);

        items.stream()
                .forEach(inv::addItem);

        return inv;

    }

    public static Inventory getListView(String type) {

        ArrayList<Player> onlinePlayerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        Inventory inv = Bukkit.createInventory(null, 54, "Bounty List: " + type);

        switch(type) {

            case "Place":

                for(int i = 0; i < onlinePlayerList.size(); i++) {

                    Player c = onlinePlayerList.get(i);
                    if(PDCUtil.isEnabled(c) && !PDCUtil.hasBountyPlacer(c) && PDCUtil.isOffShield(c)) {

                        ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD, 1);

                        ArrayList<String> desc = new ArrayList<>();
                        SkullMeta meta = (SkullMeta) playerSkull.getItemMeta();
                        meta.setOwningPlayer(onlinePlayerList.get(i));
                        meta.setDisplayName(onlinePlayerList.get(i).getName());
                        desc.add(ChatColor.GOLD + "Click to place bounty");
                        meta.setLore(desc);

                        playerSkull.setItemMeta(meta);

                        inv.addItem(playerSkull);

                    }

                }

                break;

            case "View":

                for(int i = 0; i < onlinePlayerList.size(); i++) {

                    Player c = onlinePlayerList.get(i);
                    if(PDCUtil.isEnabled(c) && PDCUtil.hasBountyPlacer(c) && !PDCUtil.hasBountyHunter(c)) {

                        ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD, 1);

                        ArrayList<String> desc = new ArrayList<>();
                        SkullMeta meta = (SkullMeta) playerSkull.getItemMeta();
                        meta.setOwningPlayer(onlinePlayerList.get(i));
                        meta.setDisplayName(onlinePlayerList.get(i).getName());
                        desc.add(ChatColor.GOLD + "Click to view bounty");
                        desc.add(ChatColor.AQUA + "Bounty Placer: " + PlayerUtil.getPlayer(PDCUtil.getBountyPlacer(onlinePlayerList.get(i))).getName());
                        meta.setLore(desc);

                        playerSkull.setItemMeta(meta);

                        inv.addItem(playerSkull);

                    }

                }

                break;

            case "Active":

                for(int i = 0; i < onlinePlayerList.size(); i++) {

                    Player c = onlinePlayerList.get(i);
                    if(PDCUtil.isEnabled(c) && PDCUtil.hasBountyPlacer(c) && PDCUtil.hasBountyHunter(c)) {

                        ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD, 1);

                        ArrayList<String> desc = new ArrayList<>();
                        SkullMeta meta = (SkullMeta) playerSkull.getItemMeta();
                        meta.setOwningPlayer(onlinePlayerList.get(i));
                        meta.setDisplayName(onlinePlayerList.get(i).getName());
                        desc.add(ChatColor.GOLD + "Click to view bounty");
                        desc.add(ChatColor.AQUA + "Bounty Placer: " + PlayerUtil.getPlayer(PDCUtil.getBountyPlacer(c)).getName());
                        desc.add(ChatColor.AQUA + "Bounty Hunter: " + PlayerUtil.getPlayer(PDCUtil.getBountyHunter(c)).getName());
                        meta.setLore(desc);

                        playerSkull.setItemMeta(meta);

                        inv.addItem(playerSkull);

                    }

                }

                break;

        }

        return inv;

    }

    // WHEN YOU CHANGE ANY NAME, DON'T FORGET TO CHANGE THE LISTENER! ! ! ! !
    public static Inventory getMainView(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, "Bounty Office");

        // Enable
        if(!PDCUtil.isEnabled(p)){

            ItemStack enableButton = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
            ArrayList<String> enableDesc = new ArrayList<>();
            ItemMeta meta = enableButton.getItemMeta();

            enableDesc.add(ChatColor.GOLD + "Click to enable Bounty mode");
            enableDesc.add(ChatColor.RED + "WARNING: Once you opt in, there's no opting out");
            meta.setDisplayName("Bounty System Disabled");
            meta.setLore(enableDesc);

            enableButton.setItemMeta(meta);
            
            inv.addItem(enableButton);

        }
        else {

            ItemStack enableButton = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
            ArrayList<String> enableDesc = new ArrayList<>();
            ItemMeta meta = enableButton.getItemMeta();

            enableDesc.add(ChatColor.GREEN + "You are already in bounty mode");
            enableDesc.add(ChatColor.GREEN + "You will exit bounty mode when someone places and collects a bounty on you");
            meta.setDisplayName("Bounty System Enabled");
            meta.setLore(enableDesc);

            enableButton.setItemMeta(meta);
            
            inv.addItem(enableButton);

        }

        // Place bounty
        ItemStack placeButton = new ItemStack(Material.PAPER, 1);
        ArrayList<String> placeDesc = new ArrayList<>();
        ItemMeta placeMeta = placeButton.getItemMeta();

        placeMeta.setDisplayName("Place Bounty");
        placeDesc.add("Click here to place a bounty or view available targets");
        placeMeta.setLore(placeDesc);

        placeButton.setItemMeta(placeMeta);

        inv.addItem(placeButton);

        // View/Accept
        ItemStack viewButton = new ItemStack(Material.PAPER, 1);
        ArrayList<String> viewDesc = new ArrayList<>();
        ItemMeta viewMeta = viewButton.getItemMeta();

        viewMeta.setDisplayName("View Bounties");
        viewDesc.add("Click here to view available bounties");
        viewMeta.setLore(placeDesc);

        viewButton.setItemMeta(viewMeta);

        inv.addItem(viewButton);
        
        // Active bounties NOT IMPLEMENTED

        ItemStack activeButton = new ItemStack(Material.OAK_SIGN, 1);
        ItemMeta activeMeta = activeButton.getItemMeta();
        ArrayList<String> activeDesc = new ArrayList<>();

        activeMeta.setDisplayName("Currently Active Bounties");
        activeDesc.add("Click here to view already accepted bounties");
        activeDesc.add(ChatColor.BOLD + "TO BE IMPLEMENTED.");
        activeMeta.setLore(activeDesc);

        activeButton.setItemMeta(activeMeta);

        // Collect page
        ItemStack collectButton = new ItemStack(Material.CHEST, 1);
        ArrayList<String> collectDesc = new ArrayList<>();
        ItemMeta collectMeta = collectButton.getItemMeta();

        if(PDCUtil.getCollectItems(p).size() > 0){ //I THINK IT'S getCollectItems, but could be getRewardItems???
            collectButton = new ItemStack(Material.CHEST, 1);
            collectDesc.add("Collect your hard earned rewards here");
            collectMeta.setDisplayName("Collect Rewards");  
        }
        else{
            collectButton = new ItemStack(Material.ENDER_CHEST, 1);
            collectDesc.add("You have no items to collect");
            collectMeta.setDisplayName("No rewards pending");  
        }

 
        collectMeta.setLore(collectDesc);

        collectButton.setItemMeta(collectMeta);

        inv.addItem(collectButton);

        // Stats
        ItemStack statsButton = new ItemStack(Material.PLAYER_HEAD, 1);
        ArrayList<String> statsDesc = new ArrayList<>();
        SkullMeta statsSkullMeta = (SkullMeta) statsButton.getItemMeta();
        statsSkullMeta.setOwningPlayer(p);
        statsSkullMeta.setDisplayName(ChatColor.BOLD + "Your stats:");

        statsDesc.add("Total Bounties Succeeded: " + StatsUtil.getBountyKills(p));
        statsDesc.add("Total Bounties Failed: " + StatsUtil.getBountyFailed(p));
        statsDesc.add("Total Bounties Survived: " + StatsUtil.getBountySurvived(p));

        statsSkullMeta.setLore(statsDesc);

        statsButton.setItemMeta(statsSkullMeta);

        inv.addItem(statsButton);

        // Help
        ItemStack helpButton = new ItemStack(Material.BOOK, 1);
        ArrayList<String> helpDesc = new ArrayList<>();
        ItemMeta helpMeta = helpButton.getItemMeta();
        helpMeta.setDisplayName(ChatColor.BOLD + "How to use the Bounty plugin: ");

        helpDesc.add("I don't get paid enought to write strings");
        // Add more description

        helpMeta.setLore(helpDesc);

        helpButton.setItemMeta(helpMeta);

        inv.addItem(helpButton);

        // Place cd
        ItemStack placeCooldownButton = new ItemStack(Material.PAPER, 1);
        ArrayList<String> placeCooldownDesc = new ArrayList<>();
        ItemMeta placeCooldownMeta = placeCooldownButton.getItemMeta();
        placeCooldownMeta.setDisplayName(ChatColor.AQUA + "Bounty Place Cooldown");

        placeCooldownDesc.add("Time remaining: " + PDCUtil.getRemainingPlaceCooldown(p));

        placeCooldownMeta.setLore(placeCooldownDesc);

        placeCooldownButton.setItemMeta(placeCooldownMeta);

        inv.addItem(placeCooldownButton);

        // Shield cd
        ItemStack shieldRemainingButton = new ItemStack(Material.SHIELD, 1);
        ArrayList<String> shieldRemainingDesc = new ArrayList<>();
        ItemMeta shieldRemainingMeta = shieldRemainingButton.getItemMeta();
        shieldRemainingMeta.setDisplayName(ChatColor.AQUA + "Shield Duration Remaining: ");

        shieldRemainingDesc.add("Time remaining: " + PDCUtil.getRemainingShieldTime(p));

        shieldRemainingMeta.setLore(shieldRemainingDesc);

        shieldRemainingButton.setItemMeta(shieldRemainingMeta);

        inv.addItem(shieldRemainingButton);

        return inv;

    }

}
