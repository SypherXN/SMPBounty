package com.sypherxn.smpbounty.util;

import com.sypherxn.smpbounty.SMPBounty;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PDCUtil {

    private static PersistentDataContainer data;
    private static UUID blankUUID = UUID.fromString("8205c038-273f-42d9-86f9-45a909c5fbe2");

    //Key - "EnableState"
    public static void setEnableState(Player p, String state) {
        data = p.getPersistentDataContainer();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "EnableState"), PersistentDataType.STRING, state);
    }

    public static String getEnableState(Player p) {
        data = p.getPersistentDataContainer();
        return data.get(new NamespacedKey(SMPBounty.getPlugin(), "EnableState"), PersistentDataType.STRING);
    }

    public static Boolean isEnabled(Player p) {
        if(getEnableState(p).equalsIgnoreCase("Enabled")) {
            return true;
        }
        return false;
    }

    //Key - "Hunting"
    public static UUID getHunting(Player p) {
        data = p.getPersistentDataContainer();
        if(data.get(new NamespacedKey(SMPBounty.getPlugin(), "Hunting"), PersistentDataType.STRING).isEmpty()) {

            return blankUUID;

        }
        UUID huntingUUID = UUID.fromString(data.get(new NamespacedKey(SMPBounty.getPlugin(), "Hunting"), PersistentDataType.STRING));
        return huntingUUID;
    }

    public static void setHunting(Player p, String huntingName) {
        data = p.getPersistentDataContainer();
        UUID huntingUUID = PlayerUtil.getPlayer(huntingName).getUniqueId();
        String huntingUUIDString = huntingUUID.toString();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "Hunting"), PersistentDataType.STRING, huntingUUIDString);
    }

    public static void setHunting(Player p, UUID huntingUUID) {
        data = p.getPersistentDataContainer();
        String huntingUUIDString = huntingUUID.toString();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "Hunting"), PersistentDataType.STRING, huntingUUIDString);
    }

    public static Boolean hasHunting(Player p) {
        data = p.getPersistentDataContainer();
        if(data.get(new NamespacedKey(SMPBounty.getPlugin(), "Hunting"), PersistentDataType.STRING).isEmpty()) {
            return false;
        }
        return true;
    }

    public static void clearHunting(Player p) {
        data = p.getPersistentDataContainer();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "Hunting"), PersistentDataType.STRING, "");
    }

    //Key - "Targeting"
    public static UUID getTargeting(Player p) {
        data = p.getPersistentDataContainer();
        if(data.get(new NamespacedKey(SMPBounty.getPlugin(), "Targeting"), PersistentDataType.STRING).isEmpty()) {

            return blankUUID;

        }
        UUID huntingUUID = UUID.fromString(data.get(new NamespacedKey(SMPBounty.getPlugin(), "Targeting"), PersistentDataType.STRING));
        return huntingUUID;
    }

    public static void setTargeting(Player p, String targetingName) {
        data = p.getPersistentDataContainer();
        UUID targetingUUID = PlayerUtil.getPlayer(targetingName).getUniqueId();
        String targetingUUIDString = targetingUUID.toString();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "Targeting"), PersistentDataType.STRING, targetingUUIDString);
    }

    public static void setTargeting(Player p, UUID targetingUUID) {
        data = p.getPersistentDataContainer();
        String targetingUUIDString = targetingUUID.toString();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "Targeting"), PersistentDataType.STRING, targetingUUIDString);
    }

    public static Boolean hasTargeting(Player p) {
        data = p.getPersistentDataContainer();
        if(data.get(new NamespacedKey(SMPBounty.getPlugin(), "Targeting"), PersistentDataType.STRING).isEmpty()) {
            return false;
        }
        return true;
    }

    public static void clearTargeting(Player p) {
        data = p.getPersistentDataContainer();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "Targeting"), PersistentDataType.STRING, "");
    }

    //Key - "BountyHunter"
    public static UUID getBountyHunter(Player p) {
        data = p.getPersistentDataContainer();
        if(data.get(new NamespacedKey(SMPBounty.getPlugin(), "BountyHunter"), PersistentDataType.STRING).isEmpty()) {

            return blankUUID;

        }
        UUID bountyHunterUUID = UUID.fromString(data.get(new NamespacedKey(SMPBounty.getPlugin(), "BountyHunter"), PersistentDataType.STRING));
        return bountyHunterUUID;
    }

    public static void setBountyHunter(Player p, String bountyHunterName) {
        data = p.getPersistentDataContainer();
        UUID bountyHunterUUID = PlayerUtil.getPlayer(bountyHunterName).getUniqueId();
        String bountyHunterUUIDString = bountyHunterUUID.toString();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountyHunter"), PersistentDataType.STRING, bountyHunterUUIDString);
    }

    public static void setBountyHunter(Player p, UUID bountyHunterUUID) {
        data = p.getPersistentDataContainer();
        String bountyHunterUUIDString = bountyHunterUUID.toString();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountyHunter"), PersistentDataType.STRING, bountyHunterUUIDString);
    }

    public static Boolean hasBountyHunter(Player p) {
        data = p.getPersistentDataContainer();
        if(data.get(new NamespacedKey(SMPBounty.getPlugin(), "BountyHunter"), PersistentDataType.STRING).isEmpty()) {
            return false;
        }
        return true;
    }

    public static void clearBountyHunter(Player p) {
        data = p.getPersistentDataContainer();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountyHunter"), PersistentDataType.STRING, "");
    }

    //Key - "BountyPlacer"
    public static UUID getBountyPlacer(Player p) {
        data = p.getPersistentDataContainer();
        if(data.get(new NamespacedKey(SMPBounty.getPlugin(), "BountyPlacer"), PersistentDataType.STRING).isEmpty()) {

            return blankUUID;

        }
        UUID bountyPlacerUUID = UUID.fromString(data.get(new NamespacedKey(SMPBounty.getPlugin(), "BountyPlacer"), PersistentDataType.STRING));
        return bountyPlacerUUID;
    }

    public static void setBountyPlacer(Player p, String bountyPlacerName) {
        data = p.getPersistentDataContainer();
        UUID bountyPlacerUUID = PlayerUtil.getPlayer(bountyPlacerName).getUniqueId();
        String bountyPlacerUUIDString = bountyPlacerUUID.toString();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountyPlacer"), PersistentDataType.STRING, bountyPlacerUUIDString);
    }

    public static void setBountyPlacer(Player p, UUID bountyPlacerUUID) {
        data = p.getPersistentDataContainer();
        String bountyPlacerUUIDString = bountyPlacerUUID.toString();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountyPlacer"), PersistentDataType.STRING, bountyPlacerUUIDString);
    }

    public static Boolean hasBountyPlacer(Player p) {
        data = p.getPersistentDataContainer();
        if(data.get(new NamespacedKey(SMPBounty.getPlugin(), "BountyPlacer"), PersistentDataType.STRING).isEmpty()) {
            return false;
        }
        return true;
    }

    public static void clearBountyPlacer(Player p) {
        data = p.getPersistentDataContainer();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountyPlacer"), PersistentDataType.STRING, "");
    }

    //Key - "RewardItems"
    public static void setRewardItems(Player p, List<ItemStack> items) {
        data = p.getPersistentDataContainer();
        String encodedData = InventoryUtil.inventoryToString(items);
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "RewardItems"), PersistentDataType.STRING, encodedData);
    }

    public static ArrayList<ItemStack> getRewardItems(Player p) {
        data = p.getPersistentDataContainer();
        ArrayList<ItemStack> items = new ArrayList<>();
        items = InventoryUtil.stringToInventory(data.get(new NamespacedKey(SMPBounty.getPlugin(), "RewardItems"), PersistentDataType.STRING));
        return items;
    }

    public static void clearRewardItems(Player p) {
        data = p.getPersistentDataContainer();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "RewardItems"), PersistentDataType.STRING, "");
    }

    //Key - "CollectItems"
    public static void setCollectItems(Player p, List<ItemStack> items) {
        data = p.getPersistentDataContainer();
        String encodedData = InventoryUtil.inventoryToString(items);
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "CollectItems"), PersistentDataType.STRING, encodedData);
    }

    public static ArrayList<ItemStack> getCollectItems(Player p) {
        data = p.getPersistentDataContainer();
        ArrayList<ItemStack> items = new ArrayList<>();
        items = InventoryUtil.stringToInventory(data.get(new NamespacedKey(SMPBounty.getPlugin(), "CollectItems"), PersistentDataType.STRING));
        return items;
    }

    public static void clearCollectItems(Player p) {
        data = p.getPersistentDataContainer();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "CollectItems"), PersistentDataType.STRING, "");
    }

    //Key - "PlaceTime"
    private static long placeCooldownTime = 86400;
    public static void setPlaceTimeCurrent(Player p) {
        data = p.getPersistentDataContainer();
        long currentTime = System.currentTimeMillis();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "PlaceTime"), PersistentDataType.LONG, currentTime);
    }

    public static long getPlaceTime(Player p) {
        data = p.getPersistentDataContainer();
        long placeTime = data.get(new NamespacedKey(SMPBounty.getPlugin(), "PlaceTime"), PersistentDataType.LONG);
        return placeTime;
    }

    public static void clearPlaceTime(Player p) {
        data = p.getPersistentDataContainer();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "PlaceTime"), PersistentDataType.LONG, (long)0);
    }

    public static String getRemainingPlaceCooldown(Player p) {
        data = p.getPersistentDataContainer();
        String remainingCooldown = "";
        long placeTimeMillis = getPlaceTime(p);
        long cooldownSeconds = ((placeTimeMillis / 1000) + placeCooldownTime) - (System.currentTimeMillis() / 1000);
        long hours = cooldownSeconds / 3600;
        long minutes = (cooldownSeconds / 60) % 60;
        long seconds = cooldownSeconds % 60;
        remainingCooldown = "" + hours + " hours, " + minutes + " minutes, and " + seconds + " seconds";
        return remainingCooldown;
    }

    public static Boolean isOffPlaceCooldown(Player p) {

        if((getPlaceTime(p) / 1000) > placeCooldownTime) return false;
        else return true;

    }

    //Key - "ShieldTime"
    private static long shieldLength = 21600;
    public static void setShieldTimeCurrent(Player p) {
        data = p.getPersistentDataContainer();
        long currentTime = System.currentTimeMillis();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "ShieldTime"), PersistentDataType.LONG, currentTime);
    }

    public static long getShieldTime(Player p) {
        data = p.getPersistentDataContainer();
        long shieldTime = data.get(new NamespacedKey(SMPBounty.getPlugin(), "PlaceTime"), PersistentDataType.LONG);
        return shieldTime;
    }

    public static void clearShieldTime(Player p) {
        data = p.getPersistentDataContainer();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "ShieldTime"), PersistentDataType.LONG, (long)0);
    }

    public static String getRemainingShieldTime(Player p) {
        data = p.getPersistentDataContainer();
        String remainingShield = "";
        long placeTimeMillis = getShieldTime(p);
        long cooldownSeconds = ((placeTimeMillis / 1000) + shieldLength) - (System.currentTimeMillis() / 1000);
        long hours = cooldownSeconds / 3600;
        long minutes = (cooldownSeconds / 60) % 60;
        long seconds = cooldownSeconds % 60;
        remainingShield = "" + hours + " hours, " + minutes + " minutes, and " + seconds + " seconds";
        return remainingShield;
    }

    public static Boolean isOffShield(Player p) {

        if((getShieldTime(p) / 1000) > shieldLength) return false;
        else return true;

    }

}
