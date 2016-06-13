package me.TMT.LHMCShop;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.TMT.LHMCShop.Main;

public class Buy implements CommandExecutor {
	
	public int invSpace (PlayerInventory inv, Material m) {
		  int count = 0;
		  for (int slot = 0; slot < 36; slot ++) {
		    ItemStack is = inv.getItem(slot);
		    if (is == null) {
		      count += m.getMaxStackSize();
		    }
		    if (is != null) {
		      if (is.getType() == m){
		        count += (m.getMaxStackSize() - is.getAmount());
		      }
		    }
		  }
		  return count;
		}
	
	public void give (PlayerInventory p, Material m, Integer a) {
		
	}

    @SuppressWarnings("deprecation")

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) { // Only continue if command sender is a player.
            sender.sendMessage(Main.chatTag + "Only players can use /buy");
            return false;
        }   
        
        Main.getInstance().getConfig();
        
        Player p = (Player) sender;
        PlayerInventory pi = p.getInventory();	
            
    	if (cmd.getName().equalsIgnoreCase("sbuy")) { // /sbuy command for buying items
    		
    		// Command format: /sbuy <item> <amount>
    		if (args.length == 2) {
    			
    			if (Main.getInstance().getConfig().contains(args[0])) { // Only continue if <item> is in config.
    			
                	int a = Integer.parseInt(args[1]);
                	double price = me.TMT.LHMCShop.Price.buyPrice(a, args[0]);
                	
                	if (invSpace(pi, Material.valueOf(Main.getInstance().getConfig().getString(args[0] + ".enum"))) >= a){
                		if (Main.economy.getBalance(p.getName()) >= price) { // Only continue if player can afford items.
        		    		Main.economy.withdrawPlayer(p.getName(), price);// Takes payment
        		    		ItemStack purchase = new ItemStack(Material.valueOf(Main.getInstance().getConfig().getString(args[0] + ".enum")), a); // Create a new ItemStack of the material and amount specified by player
        		    		pi.addItem(purchase);// Adds chosen item stack to player inventory
        		    		sender.sendMessage(Main.chatTag + "You bought " + args[1].toString() + " " + args[0].toString() + " for $" + Double.toString(price));
                    	}else{
                    		sender.sendMessage(Main.chatTag + "You can't afford that.");
                    	}
                	}else{
                		sender.sendMessage(Main.chatTag + "Your inventory doesn't have enough space.");
                	}
                	
                	
    				
    			}

    		}
    		
    		// Command format: /sbuy <item> 
    		else if (args.length == 1) {
    			
    			if (Main.getInstance().getConfig().contains(args[0])) { // Only continue if <item> is in config.
    				double price = me.TMT.LHMCShop.Price.buyPrice(1, args[0]);
                	
    				if (invSpace(pi, Material.valueOf(Main.getInstance().getConfig().getString(args[0] + ".enum"))) >= 1){
    					if (Main.economy.getBalance(p.getName()) >= price) { // Only continue if player can afford items.
                    		
        		    		Main.economy.withdrawPlayer(p.getName(), price);// Takes payment
        		    		ItemStack purchase = new ItemStack(Material.valueOf(Main.getInstance().getConfig().getString(args[0] + ".enum")), 1); // Create a new ItemStack of the material and amount specified by player
        		    		pi.addItem(purchase);// Adds chosen item stack to player inventory
        		    		sender.sendMessage(Main.chatTag + "You bought a " + args[0].toString() + " for $" + Double.toString(price));
                    	}else{
                    		sender.sendMessage(Main.chatTag + "You can't afford that.");
                    	}
    				}else{
    					sender.sendMessage(Main.chatTag + "Your inventory doesn't have enough space.");
    				}
                	
    			}
            	
    		}else{
    			sender.sendMessage(Main.chatTag + "Error: Invalid Arguments");
    		}
	    
    		return true;
	    }
    	return false;
	    	
   }
	
}
