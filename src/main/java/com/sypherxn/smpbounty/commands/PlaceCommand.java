package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.PDCUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class PlaceCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        if(PDCUtil.hasHunting(p)) {

            p.sendMessage("You cannot place a bounty while you are hunting another player!");
            return;

        }

        if(PDCUtil.hasTargeting(p)) {

            p.sendMessage("You cannot place more then one bounty at a time");
            return;

        }

        if(!PDCUtil.isOffPlaceCooldown(p)) {

            String cooldownRemaining = PDCUtil.getRemainingPlaceCooldown(p);
            p.sendMessage("You cannot place another bounty for: " + cooldownRemaining);
            return;

        }

        if(!PDCUtil.getCollectItems(p).isEmpty()) {

            p.sendMessage("You cannot place a bounty while you have items in your collection bin. Use /bounty collect to take out the items");
            return;

        }

        String targetName = args[1];
        Player target = Bukkit.getPlayerExact(targetName);
        UUID targetUUID = target.getUniqueId();

        if(!PDCUtil.isOffShield(target)) {

            String shieldRemaining = PDCUtil.getRemainingShieldTime(target);
            p.sendMessage("" + targetName + " has a shield for: " + shieldRemaining);
            return;

        }

        if(PDCUtil.hasBountyPlacer(target)) {

            p.sendMessage(target.getName() + " already has a bounty on them");
            return;

        }

        if(targetUUID.equals(null)) {

            p.sendMessage(targetName + " could not be found");
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
