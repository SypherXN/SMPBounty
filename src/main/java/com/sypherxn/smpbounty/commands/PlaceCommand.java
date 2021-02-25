package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PDCUtil;
import com.sypherxn.smpbounty.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class PlaceCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        if(!PDCUtil.isEnabled(p)) {

            ChatUtil.sendMessage(p, "You must be bounty-enabled to use this command");
            return;

        }

        if(PDCUtil.hasHunting(p)) {

            ChatUtil.sendMessage(p, "You cannot place a bounty while you are hunting another player!");
            return;

        }

        if(PDCUtil.hasTargeting(p)) {

            ChatUtil.sendMessage(p, "You cannot place more then one bounty at a time");
            return;

        }

        if(!PDCUtil.isOffPlaceCooldown(p)) {

            String cooldownRemaining = PDCUtil.getRemainingPlaceCooldown(p);
            ChatUtil.sendMessage(p, "You cannot place another bounty for: " + cooldownRemaining);
            return;

        }

        if(!PDCUtil.getCollectItems(p).isEmpty()) {

            ChatUtil.sendMessage(p, "You cannot place a bounty while you have items in your collection bin. Use /bounty collect to take out the items");
            return;

        }

        String targetName = args[1];
        Player target = PlayerUtil.getPlayer(targetName);
        UUID targetUUID = target.getUniqueId();

        if(!PDCUtil.isOffShield(target)) {

            String shieldRemaining = PDCUtil.getRemainingShieldTime(target);
            ChatUtil.sendMessage(p, "" + targetName + " has a shield for: " + shieldRemaining);
            return;

        }

        if(PDCUtil.hasBountyPlacer(target)) {

            ChatUtil.sendMessage(p, target.getName() + " already has a bounty on them");
            return;

        }

        if(targetUUID.equals(null)) {

            ChatUtil.sendMessage(p, targetName + " could not be found");
            return;

        }

        targetName = target.getName();

        Inventory bountyPlace = Bukkit.createInventory(p, 9, "Bounty Place: " + targetName);
        p.openInventory(bountyPlace);

    }

    @Override
    public String name() {
        return SMPBounty.getPlugin().commandManager.place;
    }

    @Override
    public String info() {
        return "Place a bounty on another bounty-enabled player";
    }

    @Override
    public String[] aliases() {
        String[] alias = {"p"};
        return alias;
    }
}
