package me.TMT.LHMCShop;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Count implements CommandExecutor {

    public static int howManyItems(PlayerInventory inv, Material type) {
    	int count = 0;
    	for (  ItemStack x : inv) {
	    	if (x != null && x.getType() == type){
	    		count += x.getAmount();
	    	}
    	}
		return count;
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("scount")) { 
	        Player p = (Player) sender;
	        PlayerInventory pi = p.getInventory();
			sender.sendMessage(Main.chatTag + "You have " + howManyItems(pi, Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum")))) + " " + args[0]);
		}
		return false;
	}

}
