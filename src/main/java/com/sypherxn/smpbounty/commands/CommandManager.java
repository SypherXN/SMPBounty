package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import com.sypherxn.smpbounty.gui.GUI;
import com.sypherxn.smpbounty.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();
    private ArrayList<SubCommand> modCommands = new ArrayList<SubCommand>();
    private ArrayList<SubCommand> allCommands = new ArrayList<SubCommand>();

    private SMPBounty plugin = SMPBounty.getPlugin();

    //Main command
    public String main = "bounty";

    //Public Commands
    public String help = "help";
    public String enable = "enable";
    public String place = "place";
    public String collect = "collect";
    public String cancel = "cancel";
    public String accept = "accept";
    public String view = "view";
    public String list = "list";
    public String stats = "stats";

    //Moderation Command
    public String clear = "clear";
    public String reset = "reset";
    public String set = "set";
    public String get = "get";

    public void setup() {

        //Set main /bounty command
        plugin.getCommand(main).setExecutor(this);

        //Add public commands
        this.commands.add(new HelpCommand());
        this.commands.add(new EnableCommand());
        this.commands.add(new PlaceCommand());
        this.commands.add(new CollectCommand());
        this.commands.add(new CancelCommand());
        this.commands.add(new AcceptCommand());
        this.commands.add(new ViewCommand());
        this.commands.add(new ListCommand());

        //Add moderation commands
        this.modCommands.add(new ClearCommand());
        this.modCommands.add(new GetCommand());

        for(int i = 0; i < commands.size(); i++) {

            allCommands.add(commands.get(i));

        }

        for(int i = 0; i < modCommands.size(); i++) {

            allCommands.add(modCommands.get(i));

        }

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can use commands for this plugin!");
            return false;
        }

        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase(main)) {

            if(args.length == 0) {

                Inventory inv = GUI.getMainView(p);
                p.openInventory(inv);

                return true; //Code works in mysterious ways

            }

            SubCommand target = this.get(args[0]);

            if (target == null) {

                ChatUtil.sendMessage(p, "Invalid command. Type /bounty help for help");
                return true;

            }

            ArrayList<String> arrayList = new ArrayList<String>();

            arrayList.addAll(Arrays.asList(args));
            arrayList.remove(0);

            try{

                target.onCommand(p,args);

            } catch (Exception e) {

                System.out.println(e);
                e.printStackTrace();

            }

        }

        return true;
    }

    private SubCommand get(String name) {

        Iterator<SubCommand> subcommands = this.allCommands.iterator();

        while (subcommands.hasNext()) {
            SubCommand sc = (SubCommand) subcommands.next();

            if (sc.name().equalsIgnoreCase(name)) {

                return sc;

            }

            String[] aliases;
            int length = (aliases = sc.aliases()).length;

            for (int i = 0; i < length; ++i) {

                String alias = aliases[i];
                if (name.equalsIgnoreCase(alias)) {

                    return sc;

                }

            }

        }

        return null;

    }

    public ArrayList<SubCommand> getCommands() {
        return commands;
    }
}
