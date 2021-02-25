package com.sypherxn.smpbounty.util;

import com.sypherxn.smpbounty.SMPBounty;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class StatsUtil {

    private static PersistentDataContainer data;

    //Key - "BountyKills"
    public static void incrementBountyKills(Player p) {
        data = p.getPersistentDataContainer();
        int incrementedValue = data.get(new NamespacedKey(SMPBounty.getPlugin(), "BountyKills"), PersistentDataType.INTEGER) + 1;
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountyKills"), PersistentDataType.INTEGER, incrementedValue);
    }

    public static int getBountyKills(Player p) {
        data = p.getPersistentDataContainer();
        return data.get(new NamespacedKey(SMPBounty.getPlugin(), "BountyKills"), PersistentDataType.INTEGER);
    }

    public static void resetBountyKills(Player p) {
        data = p.getPersistentDataContainer();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountyKills"), PersistentDataType.INTEGER, 0);
    }

    //Key - "BountyFailed"
    public static void incrementBountyFailed(Player p) {
        data = p.getPersistentDataContainer();
        int incrementedValue = data.get(new NamespacedKey(SMPBounty.getPlugin(), "BountyFailed"), PersistentDataType.INTEGER) + 1;
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountyFailed"), PersistentDataType.INTEGER, incrementedValue);
    }

    public static int getBountyFailed(Player p) {
        data = p.getPersistentDataContainer();
        return data.get(new NamespacedKey(SMPBounty.getPlugin(), "BountyFailed"), PersistentDataType.INTEGER);
    }

    public static void resetBountyFailed(Player p) {
        data = p.getPersistentDataContainer();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountyFailed"), PersistentDataType.INTEGER, 0);
    }

    //Key - "BountySurvived"
    public static void incrementBountySurvived(Player p) {
        data = p.getPersistentDataContainer();
        int incrementedValue = data.get(new NamespacedKey(SMPBounty.getPlugin(), "BountySurvived"), PersistentDataType.INTEGER) + 1;
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountySurvived"), PersistentDataType.INTEGER, incrementedValue);
    }

    public static int getBountySurvived(Player p) {
        data = p.getPersistentDataContainer();
        return data.get(new NamespacedKey(SMPBounty.getPlugin(), "BountySurvived"), PersistentDataType.INTEGER);
    }

    public static void resetBountySurvived(Player p) {
        data = p.getPersistentDataContainer();
        data.set(new NamespacedKey(SMPBounty.getPlugin(), "BountySurvived"), PersistentDataType.INTEGER, 0);
    }

}
