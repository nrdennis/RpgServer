package me.vilsol.betanpc.items.teleportmenu;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;
import minecade.dungeonrealms.TeleportationMechanics.TeleportationMechanics;

public class TeleportToTrollsbane implements MenuItem {

	@Override
	public void registerItem() {
		MenuItem.items.put(this.getClass(), this);
	}

	@Override
	public void execute(Player plr, ClickType click) {
		ItemStack tp = TeleportationMechanics.makeUnstackable(TeleportationMechanics.Jagged_Rocks_Tavern).clone();
		plr.getInventory().addItem(tp);
	}

	@Override
	public ItemStack getItem() {
		return new Builder(Material.BOOK)
				.setName(ChatColor.WHITE.toString() + ChatColor.BOLD + "Teleport: " + ChatColor.WHITE + "Trollsbane")
				.setLore(Arrays
						.asList(ChatColor.GRAY + "Spawn a teleportation book to " + ChatColor.BOLD + "Trollsbane"))
				.getItem();
	}

}