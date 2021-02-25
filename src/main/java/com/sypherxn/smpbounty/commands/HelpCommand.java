package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.util.ChatUtil;
import com.sypherxn.smpbounty.util.PDCUtil;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class HelpCommand extends SubCommand {

    @Override
    public void onCommand(Player p, String[] args) {

        ChatUtil.sendMessage(p, "SMPBounty Plugin");
        ArrayList<SubCommand> commands = SMPBounty.getPlugin().commandManager.getCommands();

        for(int i = 0; i < commands.size(); i++) {

            if(commands.get(i).name().equalsIgnoreCase("help")) break;

            ChatUtil.sendMessage(p, "/bounty " + commands.get(i).name() + " - " + commands.get(i).info());

        }

    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String[] aliases() {
        String[] alias = {"?"};
        return alias;
    }

}
