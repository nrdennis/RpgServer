package me.neildennis.crypticrpg.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import me.neildennis.crypticrpg.Cryptic;
import me.neildennis.crypticrpg.Manager;
import me.neildennis.crypticrpg.items.attribs.AttributeType;
import me.neildennis.crypticrpg.items.attribs.Tier;
import me.neildennis.crypticrpg.items.generator.ItemGenerator;
import me.neildennis.crypticrpg.items.generator.NameGenerator;
import me.neildennis.crypticrpg.items.generator.modifiers.ItemModifier;
import me.neildennis.crypticrpg.items.generator.modifiers.ItemModifier.ModifierType;
import me.neildennis.crypticrpg.items.generator.modifiers.TierModifier;
import me.neildennis.crypticrpg.items.listener.ItemListener;
import me.neildennis.crypticrpg.items.type.CrypticItem;
import me.neildennis.crypticrpg.items.type.CrypticItemType;
import me.neildennis.crypticrpg.items.type.armor.CrypticHelmet;
import me.neildennis.crypticrpg.items.type.weapon.CrypticSword;
import me.neildennis.crypticrpg.utils.Log;

public class ItemManager extends Manager{
	
	private static HashMap<AttributeType, ItemModifier> mods;
	
	public ItemManager(){
		NameGenerator.load();
		Cryptic.getPlugin().getServer().getPluginManager().registerEvents(new ItemListener(), Cryptic.getPlugin());
		loadMods();
	}
	
	@Override
	public void registerTasks() {
		
	}
	
	public void loadMods(){
		mods = new HashMap<AttributeType, ItemModifier>();
		
		HashMap<Tier, TierModifier> tiermods = new HashMap<Tier, TierModifier>();
		tiermods.put(Tier.ONE, new TierModifier(ModifierType.RANGE, 100, 200, 30));
		
		ArrayList<Class<? extends CrypticItem>> possible = new ArrayList<Class<? extends CrypticItem>>();
		possible.add(CrypticSword.class);
		
		ItemModifier mod = new ItemModifier(AttributeType.DAMAGE, tiermods, possible, 1.0F);
		
		mods.put(AttributeType.DAMAGE, mod);
		
		HashMap<Tier, TierModifier> armorMods = new HashMap<Tier, TierModifier>();
		armorMods.put(Tier.ONE, new TierModifier(ModifierType.STATIC, 10, 20));
		
		ArrayList<Class<? extends CrypticItem>> possibleArmor = new ArrayList<Class<? extends CrypticItem>>();
		possibleArmor.add(CrypticHelmet.class);
		
		ItemModifier modArmor = new ItemModifier(AttributeType.HEALTH, armorMods, possibleArmor, 1.0F);
		
		mods.put(AttributeType.HEALTH, modArmor);
	}
	
	public static HashMap<AttributeType, ItemModifier> getMods(){
		return mods;
	}
	
	public static CrypticItem getItemFromStack(ItemStack is){
		try {
			CrypticItemType type = CrypticItemType.fromMaterial(is.getType());
			if (type == null) return null;
			
			CrypticItem item = type.getHandleClass().newInstance();
			return item.getItemFromItemStack(is);
		} catch (InstantiationException | IllegalAccessException e) {
			Log.debug("Object wasn't made");
			return null;
		}
	}

	public static List<ItemGenerator> generateMobArmor(int level) {
		List<ItemGenerator> armor = new ArrayList<ItemGenerator>();
		
		armor.add(new ItemGenerator(CrypticItemType.HELMET).setTier(Tier.fromLevel(level)));
//		armor.add(new ItemGenerator(CrypticItemType.CHESTPLATE).setTier(Tier.fromLevel(level)));
//		armor.add(new ItemGenerator(CrypticItemType.LEGGINGS).setTier(Tier.fromLevel(level)));
//		armor.add(new ItemGenerator(CrypticItemType.BOOTS).setTier(Tier.fromLevel(level)));
		
		return armor;
	}
	
	

}
