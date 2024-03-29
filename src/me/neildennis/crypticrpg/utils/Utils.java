package me.neildennis.crypticrpg.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import me.neildennis.crypticrpg.player.CrypticPlayer;
import me.neildennis.crypticrpg.player.PlayerManager;

public class Utils {
	
	private Utils(){}
	
	public static boolean isPlayerNear(Location loc){
		for (CrypticPlayer pl : PlayerManager.getPlayers()){
			if (pl.getPlayer() == null) continue;
			double distance = pl.getPlayer().getLocation().distanceSquared(loc);
			if (distance < 10 * 10) return true;
		}
		return false;
	}
	
	public static Location getLocFromString(String str){
		String[] args = str.split(":");
		Location loc = new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
		return loc;
	}
	
	public static String getStringFromLoc(Location loc){
		return loc.getWorld().getName() + ":" + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ();
	}
	
	public static int getTierFromLevel(int level){
		if (level < 20) return 1;
		else if (level < 40) return 2;
		else if (level < 60) return 3;
		else if (level < 80) return 4;
		else if (level <= 100) return 5;
		else return 0;
	}
	
	public static ChatColor getTierColor(int tier){
		switch (tier){

		case 1: return ChatColor.WHITE;
		case 2: return ChatColor.GREEN;
		case 3: return ChatColor.BLUE;
		case 4: return ChatColor.LIGHT_PURPLE;
		case 5: return ChatColor.YELLOW;

		default: return ChatColor.WHITE;

		}
	}

}
