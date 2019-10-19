package InventoryGui;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Main.Core;

public class ShopInventory extends InventoryGui{

	
	public ShopInventory(String name)
	{
		super();
		createInventory(name);
	}
	
	private void createInventory(String name)
	{
		inventory = Bukkit.createInventory(this, 27,name);
	}
	

	public void addItem(ItemStack item,int price)
	{
		ItemStack itemShop =item.clone();
		ItemMeta meta = itemShop.getItemMeta();
		List<String> lore= meta.getLore();
		lore.add(ChatColor.YELLOW + "Cena: "+ChatColor.GOLD + price);
		meta.setLore(lore);
		itemShop.setItemMeta(meta);
		
		this.setItem(inventory.firstEmpty(), itemShop,e -> buyItem((Player) e.getWhoClicked(), item, price));
	}
	
	private void buyItem(Player p,ItemStack item,int price)
	{
		if(p.getInventory().firstEmpty() != -1 && Core.economy.withdrawPlayer(p, price).transactionSuccess())
		{
			p.getInventory().addItem(item);
		}
	}
	
	
	@Override
	public boolean onInventoryClick(Player player, int slot, ItemStack item) {
		return true;
	}

	@Override
	public boolean onInventoryOpen(Player player) {
		return false;
	}

	@Override
	public boolean onInventoryClose(Player player) {
		return false;
	}



}
