package me.TMT.LHMCShop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Sellto implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sellto")) { 
    		

			sender.sendMessage(Main.chatTag + "Sending ");
    		}
    		
    	
		return false;
	}

}
