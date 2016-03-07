package minecade.dungeonrealms.ChatMechanics.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import minecade.dungeonrealms.Main;
import minecade.dungeonrealms.ChatMechanics.ChatMechanics;
import minecade.dungeonrealms.GuildMechanics.GuildMechanics;
import minecade.dungeonrealms.PartyMechanics.PartyMechanics;
import minecade.dungeonrealms.PermissionMechanics.PermissionMechanics;
import minecade.dungeonrealms.managers.PlayerManager;

public class CommandSC implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player pl = (Player) sender;
		if (pl == null)
			return true;

		if (!PermissionMechanics.isStaff(pl)) {
			pl.sendMessage(ChatColor.RED + "You are " + ChatColor.UNDERLINE + "not" + ChatColor.RED
					+ " authorized to use this command.");
			return true;
		}

		if (args.length == 0) {
			if (!ChatMechanics.staff_only.contains(pl.getUniqueId())) {
				if (PartyMechanics.party_only.contains(pl.getUniqueId()))
					PartyMechanics.party_only.remove(pl.getUniqueId());
				if (GuildMechanics.guild_only.contains(pl.getUniqueId()))
					GuildMechanics.guild_only.remove(pl.getUniqueId());
				if (PlayerManager.getPlayerModel(pl).getToggleList().contains("globalchat"))
					PlayerManager.getPlayerModel(pl).getToggleList().remove("globalchat");
				ChatMechanics.staff_only.add(pl.getUniqueId());
				pl.sendMessage(ChatColor.GREEN + "You have toggled staff-only chat.");
			} else {
				ChatMechanics.staff_only.remove(pl.getUniqueId());
				pl.sendMessage(ChatColor.GREEN + "You toggled off staff-only chat.");
			}
			return true;
		}

		String prefix = ChatMechanics.getPlayerPrefix(pl);
		String msg = "";

		for (String s : args)
			msg += s + " ";
		msg = ChatColor.WHITE + msg;
		if (msg.endsWith(" "))
			msg = msg.substring(0, msg.length() - 1);
		if (PermissionMechanics.isStaff(pl)) {
			ChatMechanics.sendAllStaffMessage(pl, msg);
		} else
			return true;

		Main.log.info(
				ChatColor.stripColor("<" + "Staff" + ">" + " " + prefix + pl.getName() + ": " + ChatColor.WHITE + msg));
		return true;
	}

}
