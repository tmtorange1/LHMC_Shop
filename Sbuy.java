package DunnoWhatToNameThis;

import DunnoWhatToNameThis.classyClass;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Sbuy implements CommandExecutor {

    @SuppressWarnings("deprecation")

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) { // Only continue if command sender is a player.
            sender.sendMessage(classyClass.chatTag + "Only players can use /buy");
            return false;
        }   
        
        classyClass.getInstance().getConfig();
        
        Player p = (Player) sender;
        PlayerInventory pi = p.getInventory();	
            
    	if (cmd.getName().equalsIgnoreCase("sbuy")) { // /sbuy command for buying items
    		
    		// Command format: /sbuy <item> <amount>
    		if (args.length == 2) {
    			
    			if (classyClass.getInstance().getConfig().contains(args[0])) { // Only continue if <item> is in config.
    			
                	int a = Integer.parseInt(args[1]);
                	double price = DunnoWhatToNameThis.Sprice.buyPrice(a, args[0]);
                	
                	if (classyClass.economy.getBalance(p.getName()) >= price) { // Only continue if player can afford items.
    		    		classyClass.economy.withdrawPlayer(p.getName(), price);// Takes payment
    		    		ItemStack purchase = new ItemStack(Material.valueOf(classyClass.getInstance().getConfig().getString(args[0] + ".enum")), a); // Create a new ItemStack of the material and amount specified by player
    		    		pi.addItem(purchase);// Adds chosen item stack to player inventory
    		    		sender.sendMessage(classyClass.chatTag + "You bought " + args[1].toString() + " " + args[0].toString() + " for $" + Double.toString(price));
                	}else{
                		sender.sendMessage(classyClass.chatTag + "You can't afford that.");
                	}
    				
    			}

    		}
    		
    		// Command format: /sbuy <item> 
    		else if (args.length == 1) {
    			
    			if (classyClass.getInstance().getConfig().contains(args[0])) { // Only continue if <item> is in config.
    				double price = DunnoWhatToNameThis.Sprice.buyPrice(1, args[0]);
                	
                	if (classyClass.economy.getBalance(p.getName()) >= price) { // Only continue if player can afford items.
                		
    		    		classyClass.economy.withdrawPlayer(p.getName(), price);// Takes payment
    		    		ItemStack purchase = new ItemStack(Material.valueOf(classyClass.getInstance().getConfig().getString(args[0] + ".enum")), 1); // Create a new ItemStack of the material and amount specified by player
    		    		pi.addItem(purchase);// Adds chosen item stack to player inventory
    		    		sender.sendMessage(classyClass.chatTag + "You bought a " + args[0].toString() + " for $" + Double.toString(price));
                	}else{
                		sender.sendMessage(classyClass.chatTag + "You can't afford that.");
                	}
    			}
            	
    		}else{
    			sender.sendMessage(classyClass.chatTag + "Error: Invalid Arguments");
    		}
	    
    		return true;
	    }
    	return false;
	    	
   }
	
}
