package me.TMT.LHMCShop;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;

@SuppressWarnings("unused")
public class Main extends JavaPlugin {
    private static Main instance;	
	
    public Main() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }
    
	private Vault vault;
	public static Economy economy  = null;
	
	public static String chatTag = ChatColor.GOLD + "LHMC Shop // " + ChatColor.WHITE;
	
	public void loadConfiguration(){
	     getConfig().options().copyDefaults(true);
	     saveConfig();
	}
	
    @Override
    public void onEnable() {

    	loadConfiguration();
    	
    	// vault
    	this.vault = (Vault) this.getServer().getPluginManager().getPlugin("Vault");
        if (this.vault != null) {
            RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
            if (rsp != null) {
                economy = rsp.getProvider();
                this.getLogger().info(Main.chatTag + "Economy Enabled.");
            } else if (rsp == null) {
                this.getLogger().info(Main.chatTag + "No economy plugin detected.");
            }
        }
        
        // Commands
    	getCommand("sbuy").setExecutor(new Sbuy());
    	getCommand("sprice").setExecutor(new Sprice());
    	getCommand("ssell").setExecutor(new Ssell());
    	getCommand("slist").setExecutor(new Slist());
    	
    	

    	
    }
    
    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
    
    @Override
    public void onDisable() {
    	saveConfig();
    }

}

 