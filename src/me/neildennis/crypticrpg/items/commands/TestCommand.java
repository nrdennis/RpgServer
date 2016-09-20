package me.neildennis.crypticrpg.items.commands;

import org.bukkit.command.Command;

import me.neildennis.crypticrpg.CrypticCommand;
import me.neildennis.crypticrpg.items.attribs.Attribute;
import me.neildennis.crypticrpg.items.attribs.Rarity;
import me.neildennis.crypticrpg.items.attribs.Tier;
import me.neildennis.crypticrpg.items.generator.ItemGenerator;
import me.neildennis.crypticrpg.items.generator.NameGenerator;
import me.neildennis.crypticrpg.items.type.CrypticItem;
import me.neildennis.crypticrpg.items.type.CrypticItemType;
import me.neildennis.crypticrpg.player.CrypticPlayer;
import me.neildennis.crypticrpg.utils.Log;

public class TestCommand extends CrypticCommand{

	@Override
	protected void sendUsage() {
		
	}

	@Override
	public void command(CrypticPlayer pl, Command cmd, String label, String[] args) {
		CrypticItem item = new ItemGenerator(CrypticItemType.SWORD).setRarity(Rarity.COMMON).setTier(Tier.ONE).setAttribute(Attribute.DAMAGE, 100).generate();
		pl.getPlayer().getInventory().setItemInMainHand(item.generateItemStack());
	}

	@Override
	public void console(Command cmd, String label, String[] args) {
		Log.info("Cannot use from console");
		NameGenerator.load();
	}

}
