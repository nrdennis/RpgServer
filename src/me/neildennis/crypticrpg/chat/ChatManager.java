package me.neildennis.crypticrpg.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

import me.neildennis.crypticrpg.Cryptic;
import me.neildennis.crypticrpg.Manager;
import me.neildennis.crypticrpg.permission.Rank;
import me.neildennis.crypticrpg.player.CrypticPlayer;
import me.neildennis.crypticrpg.player.PlayerManager;

public class ChatManager extends Manager implements Listener{
	
	private static ConcurrentLinkedQueue<SyncMessage> syncmsg;
	private List<BukkitTask> tasks;
	
	private static String globalPrefix = ChatColor.AQUA + "<G> ";
	private static String adminPrefix = ChatColor.RED + "<A> ";
	
	@Override
	public void onEnable() {
		syncmsg = new ConcurrentLinkedQueue<SyncMessage>();
		tasks = new ArrayList<BukkitTask>();
		Cryptic.registerEvents(new ChatListener());
		registerTasks();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public void registerTasks(){
		tasks.add(Bukkit.getScheduler().runTaskTimer(Cryptic.getPlugin(), new Runnable(){

			@Override
			public void run() {
				while (!syncmsg.isEmpty()){
					SyncMessage msg = syncmsg.poll();
					if (msg.getMessage() instanceof String[]) msg.getPlayer().sendMessage((String[]) msg.getMessage());
					else msg.getPlayer().sendMessage(msg.getMessage().toString());
				}
			}
			
		}, 5L, 1L));
	}
	
	public static void sendSyncMessage(CommandSender pl, Object msg){
		syncmsg.offer(new SyncMessage(pl, msg));
	}
	
	public static void showAdmin(CrypticPlayer cpl, String message){
		String broadcast = adminPrefix + cpl.getRank().getPrefix() + cpl.getPlayer().getDisplayName() + ChatColor.GRAY + ": ";
		broadcast += ChatColor.translateAlternateColorCodes('&', message);
		
		for (CrypticPlayer cp : PlayerManager.search(pl -> pl.getRank().getPriority() >= Rank.ADMIN.getPriority())){
			cp.sendMessage(broadcast);
		}
	}
	
	public static void showGlobal(CrypticPlayer pl, String message){
		String broadcast = globalPrefix + pl.getRankData().getRank().getPrefix()
				+ pl.getPlayer().getDisplayName() + ChatColor.RESET + ": ";
		
		if (pl.getRankData().getRank().getPriority() <= Rank.ADMIN.getPriority())
			broadcast += ChatColor.translateAlternateColorCodes('&', message);
		else
			broadcast += message;
		
		Bukkit.broadcastMessage(broadcast);
	}

}
