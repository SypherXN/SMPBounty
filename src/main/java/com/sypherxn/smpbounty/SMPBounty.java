package com.sypherxn.smpbounty;

import com.sypherxn.smpbounty.commands.CommandManager;
import com.sypherxn.smpbounty.listeners.Listeners;
import com.sypherxn.smpbounty.util.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SMPBounty extends JavaPlugin {

    private static SMPBounty plugin;
    public CommandManager commandManager;

    @Override
    public void onEnable() {

        System.out.println("SMPBounty Plugin Enabled");

        plugin = this;
        commandManager = new CommandManager();
        getCommand("bounty").setExecutor(commandManager);
        commandManager.setup();
        getServer().getPluginManager().registerEvents(new Listeners(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SMPBounty getPlugin() {
        return plugin;
    }
}
