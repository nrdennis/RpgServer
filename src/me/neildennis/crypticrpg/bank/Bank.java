package me.neildennis.crypticrpg.bank;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.inventory.Inventory;

import me.neildennis.crypticrpg.cloud.CloudManager;
import me.neildennis.crypticrpg.player.CrypticPlayer;

public class Bank {
	
	private CrypticPlayer cp;
	private Inventory inv;
	private int money;
	private int level;
	
	public Bank(CrypticPlayer cp, ResultSet set){
		this.cp = cp;
		load();
	}

	public void save(){
		
	}
	
	public void load(){
		try {
			ResultSet data = CloudManager.sendQuery("SELECT * FROM banks WHERE player_id = '" + cp.getOfflinePlayer().getUniqueId() + "'");
			if (!data.next()) return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
