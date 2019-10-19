package Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import InventoryGui.ShopInventory;
import Main.ShopManager;
import net.citizensnpcs.api.event.NPCRightClickEvent;

public class NpcClickListener implements Listener{

	@EventHandler
	public void npcClickEvent(NPCRightClickEvent e)
	{
		ShopInventory inv = ShopManager.getManager().getShop(e.getNPC().getId());
		if(inv != null) e.getClicker().openInventory(inv.getInventory());
	}
}
