package me.TMT.LHMCShop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sprice implements CommandExecutor {
	
	// Determine sell price of item at any amount.
	public static double sellPrice(Integer amount, String item) {
		return amount * Main.getInstance().getConfig().getDouble((item + ".sell"));
	}
	
	// Determine buy price of item at any amount.
	public static double buyPrice(Integer amount, String item) {
		return amount * Main.getInstance().getConfig().getDouble((item + ".buy"));
	}
	
	// /sprice command - returns the price of items
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) { // Only continue if command sender is a player.
            sender.sendMessage(Main.chatTag + "Only players can use /sprice");
            return false;
        }   
        Main.getInstance().reloadConfig();
        Main.getInstance().saveConfig();
    	if (cmd.getName().equalsIgnoreCase("sprice")) { 
    		
    		// Command format: /sprice <item> <amount>
    		if (args.length == 2) {
    			if (Main.getInstance().getConfig().contains(args[0])) {
	            	int a = Integer.parseInt(args[1]);
	            	double bp = buyPrice(a, args[0]);
	            	double sp = sellPrice(a, args[0]);;
		    		sender.sendMessage(Main.chatTag + "Buy " + args[1].toString() + " " + args[0].toString() + " for $" + Double.toString(bp) + ", sell for $" + Double.toString(sp) + ".");
    			}
    		}
    		
    		// Command format: /sprice <item>
    		if (args.length == 1) {
            	double bp = buyPrice(1, args[0]);
            	double sp = sellPrice(1, args[0]);
	    		sender.sendMessage(Main.chatTag + "Buy a " + args[0].toString() + " for $" + Double.toString(bp) + ", sell for $" + Double.toString(sp) + ".");
	    	
	    	// Command format: /sprice
    		}
    		if (args.length == 0) {
    			sender.sendMessage(Main.chatTag + "Error: Invalid Arguments");
    		}
	    
    		return true;
	    }
    	return false;
	    	
   }
	
}