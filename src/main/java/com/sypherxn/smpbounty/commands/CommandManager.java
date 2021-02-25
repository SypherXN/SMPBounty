package com.sypherxn.smpbounty.commands;

import com.sypherxn.smpbounty.SMPBounty;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();
    private SMPBounty plugin = SMPBounty.getPlugin();

    public String main = "bounty";

    public String help = "help";
    public String enable = "enable";
    public String place = "place";
    public String collect = "collect";
    public String cancel = "cancel";
    public String accept = "accept";
    public String view = "view";
    public String list = "list";

    public void setup() {

        plugin.getCommand(main).setExecutor(this);

        this.commands.add(new HelpCommand());
        this.commands.add(new EnableCommand());
        this.commands.add(new PlaceCommand());
        this.commands.add(new CollectCommand());
        this.commands.add(new CancelCommand());
        this.commands.add(new AcceptCommand());
        this.commands.add(new ViewCommand());
        this.commands.add(new ListCommand());

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        System.out.println("CommandManager run");

        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can use commands for this plugin!");
            return false;
        }

        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase(main)) {

            if(args.length == 0) {

                p.sendMessage("Invalid command. Type /bounty help for help");
                return false;

            }

            SubCommand target = this.get(args[0]);

            if (target == null) {

                p.sendMessage("Invalid command. Type /bounty help for help");
                return false;

            }

            ArrayList<String> arrayList = new ArrayList<String>();

            arrayList.addAll(Arrays.asList(args));
            arrayList.remove(0);

            try{

                target.onCommand(p,args);

            } catch (Exception e) {

                p.sendMessage("An error has occurred.");
                e.printStackTrace();

            }

        }

        return true;
    }

    private SubCommand get(String name) {

        Iterator<SubCommand> subcommands = this.commands.iterator();

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


}
