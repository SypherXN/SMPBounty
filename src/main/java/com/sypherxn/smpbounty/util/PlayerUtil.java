package com.sypherxn.smpbounty.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerUtil {

    //Returns the player even if they are offline
    public static Player getPlayer(Player sender, String name) {

        Player p = Bukkit.getPlayerExact(name);
        if(p == null) {

            ChatUtil.sendMessage(sender, "Player is not currently online");
            return null;

        }

        return p;

    }

    public static Player getPlayer(Player sender, UUID id) {

        Player p = Bukkit.getPlayer(id);
        if(p == null) {

            ChatUtil.sendMessage(sender, "Player is not currently online");
            return null;

        }

        return p;

    }

    public static Player getPlayer(UUID id) {

        Player p = Bukkit.getPlayer(id);

        return p;

    }

    public static Player getPlayer(String name) {

        Player p = Bukkit.getPlayerExact(name);

        return p;

    }

}
