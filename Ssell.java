package DunnoWhatToNameThis;


import java.util.Set;
import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Ssell implements CommandExecutor {

    

    public static void removeInventoryItems(PlayerInventory inv, Material type, int amount) {
        for (ItemStack is : inv.getContents()) {
            if (is != null && is.getType() == type) {
                int newamount = is.getAmount() - amount;
                if (newamount > 0) {
                    is.setAmount(newamount);
                    break;
                } else {
                    inv.remove(is);
                    amount = -newamount;
                    if (amount == 0) break;
                }
            }
        }
    }

	@SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(classyClass.chatTag + "Only players can use /ssell");
            return false;
        }   
        
        Player p = (Player) sender;
        PlayerInventory pi = p.getInventory();
                   
    	if (cmd.getName().equalsIgnoreCase("ssell")) {
    		// Command format: /ssell <item> <amount>
    		if (args.length == 2) {
    			int count = 0;
	    		do
	    		{
	    			if (pi.contains(Material.valueOf(classyClass.getInstance().getConfig().getString((args[0] + ".enum"))))){
		    			classyClass.economy.depositPlayer(p.getName(), DunnoWhatToNameThis.Sprice.sellPrice(1, args[0]));
		    			removeInventoryItems(pi, Material.valueOf(classyClass.getInstance().getConfig().getString((args[0] + ".enum"))), 1);
		    			count += 1;
	    			}else{
	    				sender.sendMessage(classyClass.chatTag + "You don't have enough " + args[0].toString() + " to sell.");
	    				break;
	    			}
	    			
	    		}while(count < Integer.parseInt(args[1]));
	    		sender.sendMessage(classyClass.chatTag + "You sold " + Integer.toString(count) + " " + args[0].toString() + " for $" + Double.toString(DunnoWhatToNameThis.Sprice.sellPrice(count, args[0])));
    		}
    		// Command format: /sbuy <item> 
    		if (args.length == 1) {
    			// Command format: /sbuy all ---Work In Progress---
    			if (args[0].equalsIgnoreCase("all")) {
    				
    				Set<String> items = classyClass.getInstance().getConfig().getKeys(false);
					Iterator<String> iter = items.iterator();
					while (iter.hasNext()) {
						if (pi.contains(Material.valueOf(classyClass.getInstance().getConfig().getString((iter.next() + ".enum"))))) {
							classyClass.economy.depositPlayer(p.getName(), DunnoWhatToNameThis.Sprice.sellPrice(1, args[0]));
			    			removeInventoryItems(pi, Material.valueOf(classyClass.getInstance().getConfig().getString((args[0] + ".enum"))), 1);
				    		sender.sendMessage(classyClass.chatTag + "You sold a "  + args[0].toString() + " for $" + Double.toString(DunnoWhatToNameThis.Sprice.sellPrice(1, args[0])));
						}
					}
    				
    			}else{
	    			classyClass.economy.depositPlayer(p.getName(), DunnoWhatToNameThis.Sprice.sellPrice(1, args[0]));
	    			removeInventoryItems(pi, Material.valueOf(classyClass.getInstance().getConfig().getString((args[0] + ".enum"))), 1);
		    		sender.sendMessage(classyClass.chatTag + "You sold a "  + args[0].toString() + " for $" + Double.toString(DunnoWhatToNameThis.Sprice.sellPrice(1, args[0])));
    			}
    		}

    		}

		return false;	
	}
}