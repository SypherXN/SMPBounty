package com.sypherxn.smpbounty.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtil {

    private static String prefix = ChatColor.GOLD.toString() + ChatColor.BOLD + ChatColor.ITALIC.toString() + "<SMPBounty>" + ChatColor.RESET.toString() + ChatColor.AQUA;

    public static void sendMessage(Player p, String message) {

        p.sendMessage(prefix + message);

    }

    public static void sendBroadcast(String message) {

        Bukkit.broadcastMessage(prefix + message);

    }

}
