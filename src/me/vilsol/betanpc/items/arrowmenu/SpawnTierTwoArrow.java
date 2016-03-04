package me.vilsol.betanpc.items.arrowmenu;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;
import minecade.dungeonrealms.ItemMechanics.ItemMechanics;

public class SpawnTierTwoArrow implements MenuItem {

	@Override
	public void registerItem() {
		MenuItem.items.put(this.getClass(), this);
	}

	@Override
	public void execute(Player plr, ClickType click) {
		ItemStack arrow = ItemMechanics.t2_arrow.clone();
		arrow.setAmount(64);
        plr.getInventory().addItem(arrow);
	}

	@Override
	public ItemStack getItem() {
		return new Builder(Material.ARROW).setName(ChatColor.GREEN + "Spawn Tier 2 Arrow").setLore(Arrays.asList(ChatColor.GRAY + "Spawn in " + ChatColor.BOLD + "64x " + ChatColor.GREEN + "Tier 2 Arrows.")).getItem();
	}
	
}
