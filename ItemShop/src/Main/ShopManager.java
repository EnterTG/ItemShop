package Main;

import java.util.HashMap;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import InventoryGui.ShopInventory;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;

public class ShopManager {

	private static ShopManager shopManager;
	
	public static ShopManager getManager()
	{
		if(shopManager == null) shopManager = new ShopManager();
		return shopManager;
	}

	private ShopManager()
	{
		
	}
	
	private HashMap<String, ShopInventory> allShop = new HashMap<>();
	private HashMap<Integer, ShopInventory> npcShop = new HashMap<>();
	
	public void loadAllShops(Core core)
	{
		core.getConfig().getKeys(false).forEach( s-> loadShop(core, s));
	}
	
	public void loadShop(Core core , String shopName)
	{
		ShopInventory shop = new ShopInventory(shopName);
		for(String i : (List<String>)core.getConfig().getList(shopName+".items"))
		{
			String[] itemtable = i.split(":");
			ItemStack item = BukkitAdapter.adapt(MythicMobs.inst().getItemManager().getItem(itemtable[0]).get().generateItemStack(Integer.parseInt(itemtable[1])));
			shop.addItem(item,Integer.parseInt(itemtable[2]));
		}
		for(Integer i : (List<Integer>)core.getConfig().getList(shopName+".npc"))
		{
			npcShop.put((i), shop);
		}
		allShop.put(shopName, shop);
		
		System.out.println("Shop loaded: " + shopName);
	}
	
	public ShopInventory getShop(String shopName)
	{
		return allShop.get(shopName);
	}
	
	public ShopInventory getShop(int npcID)
	{
		return npcShop.get(npcID);
	}

	
}
