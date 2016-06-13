package me.TMT.LHMCShop;

//import java.util.Iterator;
//import java.util.Set;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class List implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		// Command format: /slist
		if (cmd.getName().equalsIgnoreCase("slist")) { 
			sender.sendMessage(Main.chatTag + "All valid items: ");
			for (String x: Main.getInstance().getConfig().getKeys(false)){
			    sender.sendMessage(Main.chatTag + "- " + x + " Buy: $" + me.TMT.LHMCShop.Price.buyPrice(1, x) + " Sell: $" + me.TMT.LHMCShop.Price.sellPrice(1, x));
			}
			
		}
		return false;
	}

}
