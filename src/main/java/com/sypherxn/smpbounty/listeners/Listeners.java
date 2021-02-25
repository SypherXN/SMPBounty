package com.sypherxn.smpbounty.listeners;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.gui.GUI;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PDCUtil;
import com.sypherxn.smpbounty.util.PlayerUtil;
import com.sypherxn.smpbounty.util.StatsUtil;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Listeners implements Listener {

    //Makes sure every player has all tags upon login
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        PersistentDataContainer data = p.getPersistentDataContainer();

        //EnableState
        if(!data.has(new NamespacedKey(SMPBounty.getPlugin(), "EnableState"), PersistentDataType.STRING)) {
            PDCUtil.setEnableState(p, "Disabled");
        }

        //Hunting
        if(!data.has(new NamespacedKey(SMPBounty.getPlugin(), "Hunting"), PersistentDataType.STRING)) {
            PDCUtil.clearHunting(p);
        }

        //Targeting
        if(!data.has(new NamespacedKey(SMPBounty.getPlugin(), "Targeting"), PersistentDataType.STRING)) {
            PDCUtil.clearTargeting(p);
        }

        //BountyHunter
        if(!data.has(new NamespacedKey(SMPBounty.getPlugin(), "BountyHunter"), PersistentDataType.STRING)) {
            PDCUtil.clearBountyHunter(p);
        }

        //BountyPlacer
        if(!data.has(new NamespacedKey(SMPBounty.getPlugin(), "BountyPlacer"), PersistentDataType.STRING)) {
            PDCUtil.clearBountyPlacer(p);
        }

        //RewardItems
        if(!data.has(new NamespacedKey(SMPBounty.getPlugin(), "RewardItems"), PersistentDataType.STRING)) {
            data.set(new NamespacedKey(SMPBounty.getPlugin(), "RewardItems"), PersistentDataType.STRING, "");
        }

        //CollectItems
        if(!data.has(new NamespacedKey(SMPBounty.getPlugin(), "CollectItems"), PersistentDataType.STRING)) {
            data.set(new NamespacedKey(SMPBounty.getPlugin(), "CollectItems"), PersistentDataType.STRING, "");
        }

        //PlaceTime
        if(!data.has(new NamespacedKey(SMPBounty.getPlugin(), "PlaceTime"), PersistentDataType.LONG)) {
            data.set(new NamespacedKey(SMPBounty.getPlugin(), "PlaceTime"), PersistentDataType.LONG, (long)0);
        }

        //ShieldTime
        if(!data.has(new NamespacedKey(SMPBounty.getPlugin(), "ShieldTime"), PersistentDataType.LONG)) {
            data.set(new NamespacedKey(SMPBounty.getPlugin(), "ShieldTime"), PersistentDataType.LONG, (long)0);
        }

        //BountyKills
        if(!data.has(new NamespacedKey(SMPBounty.getPlugin(), "BountyKills"), PersistentDataType.INTEGER)) {
            data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountyKills"), PersistentDataType.INTEGER, 0);
        }

        //BountyFailed
        if(!data.has(new NamespacedKey(SMPBounty.getPlugin(), "BountyFailed"), PersistentDataType.INTEGER)) {
            data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountyFailed"), PersistentDataType.INTEGER, 0);
        }

        //BountySurvived
        if(!data.has(new NamespacedKey(SMPBounty.getPlugin(), "BountySurvived"), PersistentDataType.INTEGER)) {
            data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountySurvived"), PersistentDataType.INTEGER, 0);
        }

    }

    //Check closing of Bounty Place and ensure that there are items inside the inventory
    @EventHandler
    public void bountyPlaceClose(InventoryCloseEvent e) {

        if(e.getView().getTitle().length() < 13) { return; }

        String placeCheck = e.getView().getTitle().substring(0,13);
        Player p = (Player) e.getPlayer();

        if(placeCheck.equalsIgnoreCase("Bounty Place:")) {

            if(e.getInventory().isEmpty()) {

                ChatUtil.sendMessage(p, "You cannot place a bounty with no reward!");
                return;

            }

            ArrayList<ItemStack> items = new ArrayList<>();

            Arrays.stream(e.getInventory().getContents())
                    .filter(itemStack -> {

                        if(itemStack == null) {

                            return false;

                        }

                        return true;

                    })
                    .forEach(items::add);

            String targetName = e.getView().getTitle().substring(14);
            Player target = PlayerUtil.getPlayer(targetName);
            UUID targetUUID = target.getUniqueId();

            PDCUtil.setRewardItems(target, items);
            PDCUtil.setBountyPlacer(target, p.getUniqueId());
            ChatUtil.sendMessage(target, p.getName() + " has placed a bounty on you!");

            PDCUtil.setTargeting(p, targetUUID);
            PDCUtil.setPlaceTimeCurrent(p);
            ChatUtil.sendMessage(p, "You have successfully placed a bounty on " + targetName);

        }

    }

    //Check closing of Bounty Collect and ensure that there are items
    @EventHandler
    public void bountyCollectClose(InventoryCloseEvent e) {

        if(e.getView().getTitle().length() < 15) { return; }

        String collectCheck = e.getView().getTitle().substring(0,15);
        Player p = (Player) e.getPlayer();

        if(collectCheck.equalsIgnoreCase("Bounty Collect:")) {

            if (e.getInventory().isEmpty()) {

                PDCUtil.clearCollectItems(p);
                return;

            }

            ChatUtil.sendMessage(p, "You will not be able to perform other bounty tasks with items left in your collection!");

            ArrayList<ItemStack> items = new ArrayList<>();

            Arrays.stream(e.getInventory().getContents())
                    .filter(itemStack -> {

                        if (itemStack == null) {

                            return false;

                        }

                        return true;

                    })
                    .forEach(items::add);

            PDCUtil.setCollectItems(p, items);

        }

    }

    //Cancel Taking items out of Bounty View
    @EventHandler
    public void cancelGUIClick(InventoryClickEvent e) {

        if(e.getView().getTitle().length() < 12) { return; }

        String viewCheck = e.getView().getTitle().substring(0,12);

        if(viewCheck.equalsIgnoreCase("Bounty View:")) {

            e.setCancelled(true);

        }

    }

    //Open bounty view from bounty list and cancel click
    @EventHandler
    public void listToViewClick(InventoryClickEvent e) {

        if(e.getView().getTitle().length() < 11) { return; }

        String listCheck = e.getView().getTitle().substring(0, 11);
        ItemStack clickedItem = e.getCurrentItem();
        String name = clickedItem.getItemMeta().getDisplayName();
        Player p = (Player) e.getWhoClicked();
        Player target = PlayerUtil.getPlayer(name);

        if(listCheck.equalsIgnoreCase("Bounty List")) {

            Inventory bountyView = GUI.getRewardView(target);

            p.openInventory(bountyView);

            e.setCancelled(true);

        }

    }

    //Check if player death impacts bounties
    @EventHandler
    public void playerDeathCheck(PlayerDeathEvent e) {

        if(e.getEntity().getKiller() instanceof Player) {

            Player death = e.getEntity();
            Player killer = e.getEntity().getKiller();

            UUID killerUUID = killer.getUniqueId();

            UUID bountyPlacerUUID = PDCUtil.getBountyPlacer(death);
            UUID deathHunterUUID = PDCUtil.getBountyHunter(death);

            Player bountyPlacer = PlayerUtil.getPlayer(bountyPlacerUUID);
            if(deathHunterUUID.equals(killerUUID)) {

                ChatUtil.sendBroadcast("Test");

                ChatUtil.sendBroadcast(killer.getName() + " has killed " + death.getName() + " and has collected their reward from " + bountyPlacer.getName());

                ArrayList<ItemStack> reward = PDCUtil.getRewardItems(death);
                PDCUtil.setCollectItems(killer, reward);
                PDCUtil.clearHunting(killer);
                StatsUtil.incrementBountyKills(killer);
                ChatUtil.sendMessage(killer, "Use \"/bounty collect\" to retrieve your reward items!");

                PDCUtil.clearBountyHunter(death);
                PDCUtil.clearBountyPlacer(death);
                PDCUtil.clearRewardItems(death);
                PDCUtil.setEnableState(death, "Disabled");
                ChatUtil.sendMessage(death, "You are no longer bounty-enabled!");

                PDCUtil.clearTargeting(bountyPlacer);

                return;

            }

            UUID deathHuntingUUID = PDCUtil.getHunting(death);
            bountyPlacerUUID = PDCUtil.getBountyPlacer(killer);
            bountyPlacer = PlayerUtil.getPlayer(bountyPlacerUUID);

            ChatUtil.sendBroadcast(deathHuntingUUID.toString());
            ChatUtil.sendBroadcast(killerUUID.toString());

            if(deathHuntingUUID.equals(killerUUID)) {

                ChatUtil.sendBroadcast(death.getName() + " has failed to complete " + bountyPlacer.getName() + "'s bounty on " + killer.getName());

                PDCUtil.clearHunting(death);
                StatsUtil.incrementBountyFailed(death);

                ArrayList<ItemStack> reward = PDCUtil.getRewardItems(killer);
                PDCUtil.setCollectItems(bountyPlacer, reward);
                ChatUtil.sendMessage(killer, "Use \"/bounty collect\" to retrieve your bounty items!");
                PDCUtil.clearTargeting(bountyPlacer);

                PDCUtil.clearBountyHunter(killer);
                PDCUtil.clearBountyPlacer(killer);
                PDCUtil.clearRewardItems(killer);
                PDCUtil.setShieldTimeCurrent(killer);
                StatsUtil.incrementBountySurvived(killer);

                return;

            }

        }

    }

}
