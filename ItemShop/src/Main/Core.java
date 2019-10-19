package Main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import Listeners.InventoryActionListener;
import Listeners.NpcClickListener;
import net.milkbowl.vault.economy.Economy;

public class Core extends JavaPlugin
{
	
	public static Economy economy = null;
	
	
	@Override
	public void onEnable() 
	{
		setupEconomy();
		Bukkit.getPluginManager().registerEvents(new InventoryActionListener(), this);
		Bukkit.getPluginManager().registerEvents(new NpcClickListener(), this);

		ShopManager.getManager().loadAllShops(this);
	}
	
	
	private boolean setupEconomy()
	{
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}
		return (economy != null);
	}
}
