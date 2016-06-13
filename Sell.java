package me.TMT.LHMCShop;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Sell implements CommandExecutor {

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
            sender.sendMessage(Main.chatTag + "Only players can use /ssell");
            return false;
        }   
        
        Player p = (Player) sender;
        PlayerInventory pi = p.getInventory();
                   
    	if (cmd.getName().equalsIgnoreCase("ssell")) {
    		// Command format: /ssell <item> <amount>
    		if (args.length == 2) {
    			if (args[1] == "all") {
    				Main.economy.depositPlayer(p.getName(), me.TMT.LHMCShop.Price.sellPrice(Count.howManyItems(pi, (Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum"))))), args[0]));
  	    			sender.sendMessage(Main.chatTag + "You sold " + Count.howManyItems(pi, (Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum"))))) + " " + args[0] + " for $" + Double.toString(me.TMT.LHMCShop.Price.sellPrice(Count.howManyItems(pi, (Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum"))))), args[0])));
  	    			removeInventoryItems(pi, Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum"))), Count.howManyItems(pi, (Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum"))))));
    			}else if (Integer.parseInt(args[1]) >= Count.howManyItems(pi, (Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum")))))) {
    				Main.economy.depositPlayer(p.getName(), me.TMT.LHMCShop.Price.sellPrice(Count.howManyItems(pi, (Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum"))))), args[0]));
  	    			sender.sendMessage(Main.chatTag + "Not enough " + args[0]);
  	    			sender.sendMessage(Main.chatTag + "You sold " + Count.howManyItems(pi, (Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum"))))) + " " + args[0] + " for $" + Double.toString(me.TMT.LHMCShop.Price.sellPrice(Count.howManyItems(pi, (Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum"))))), args[0])));
  	    			removeInventoryItems(pi, Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum"))), Count.howManyItems(pi, (Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum"))))));
    			} else if (Integer.parseInt(args[1]) < Count.howManyItems(pi, (Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum")))))) {
    				Main.economy.depositPlayer(p.getName(), me.TMT.LHMCShop.Price.sellPrice(1, args[0]));
  	    			sender.sendMessage(Main.chatTag + "You sold " + Integer.parseInt(args[1]) + " " + args[0] + " for $" + Double.toString(me.TMT.LHMCShop.Price.sellPrice(Integer.parseInt(args[1]), args[0])));
  	    			removeInventoryItems(pi, Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum"))), Integer.parseInt(args[1]));
    			}
	    		
    		} else if (args.length == 1) { // Command format: /ssell <item> 
    			// Command format: /ssell all
    			if (args[0].equalsIgnoreCase("all")) {
					for (  String x : Main.getInstance().getConfig().getKeys(false)) {
						if (pi.contains(Material.valueOf(Main.getInstance().getConfig().getString((x + ".enum"))))) {
							sender.sendMessage(Main.chatTag + "You sold " + Count.howManyItems(pi, Material.valueOf(Main.getInstance().getConfig().getString((x + ".enum")))) + " " + x + " for $" + me.TMT.LHMCShop.Price.sellPrice(Count.howManyItems(pi, Material.valueOf(Main.getInstance().getConfig().getString((x + ".enum")))), x));
							Main.economy.depositPlayer(p.getName(), me.TMT.LHMCShop.Price.sellPrice(Count.howManyItems(pi, Material.valueOf(Main.getInstance().getConfig().getString((x + ".enum")))), x));
							removeInventoryItems(pi, Material.valueOf(Main.getInstance().getConfig().getString((x + ".enum"))), Count.howManyItems(pi, Material.valueOf(Main.getInstance().getConfig().getString((x + ".enum")))));
						}
					}	
					
				} else {
					Main.economy.depositPlayer(p.getName(), me.TMT.LHMCShop.Price.sellPrice(1, args[0]));
	    			removeInventoryItems(pi, Material.valueOf(Main.getInstance().getConfig().getString((args[0] + ".enum"))), 1);
		    		sender.sendMessage(Main.chatTag + "You sold a "  + args[0].toString() + " for $" + Double.toString(me.TMT.LHMCShop.Price.sellPrice(1, args[0])));
				}
    				
    		} else if (args.length == 0) {
    			sender.sendMessage(Main.chatTag + "Error: Invalid Arguments");
    		}
    		

	}
		return false;
}
}