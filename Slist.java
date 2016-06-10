package me.TMT.LHMCShop;

import java.util.Iterator;
import java.util.Set;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Slist implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		// Command format: /slist
		if (cmd.getName().equalsIgnoreCase("slist")) { 
			sender.sendMessage(Main.chatTag + "All valid items: ");
			Set<String> items = Main.getInstance().getConfig().getKeys(false);
			Iterator<String> iter = items.iterator();
			while (iter.hasNext()) {
			    sender.sendMessage(Main.chatTag + "- " + iter.next());
			}
			
		}
		return false;
	}

}
