package me.iran.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommands implements CommandExecutor {

	iChat plugin;
	String clear = " ";
	public static boolean slowChat = false;
	public static long seconds;
	public ChatCommands(iChat plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Player Command ONLY!");
			return true;
		}

		if (cmd.getName().equalsIgnoreCase("lockchat")
				|| cmd.getName().equalsIgnoreCase("lc")) {
			if (player.hasPermission("ichat.chat.mute")) {
				if (iChat.isLocked != true) {
					iChat.isLocked = true;
					Bukkit.broadcastMessage(ChatColor.RED.toString()
							+ "Chat has been " + ChatColor.UNDERLINE
							+ "Disabled " + ChatColor.RED + " by "
							+ ChatColor.RED.toString() + ChatColor.BOLD + "("
							+ player.getName() + ")");

				} else {
					player.sendMessage(ChatColor.RED + "Chat is already Muted");
				}

			} else {
				player.sendMessage(ChatColor.RED
						+ "You do Not have Permission to do this Command");
			}

		}

		if (cmd.getName().equalsIgnoreCase("unlock")
				|| cmd.getName().equalsIgnoreCase("ul")) {
			if (player.hasPermission("ichat.chat.unmute")) {
				if (iChat.isLocked != false) {
					iChat.isLocked = false;
					Bukkit.broadcastMessage(ChatColor.GREEN.toString()
							+ "Chat has been " + ChatColor.UNDERLINE
							+ "Enabled " + ChatColor.GREEN + " by "
							+ ChatColor.GREEN.toString() + ChatColor.BOLD + "("
							+ player.getName() + ")");
				} else {
					player.sendMessage(ChatColor.RED
							+ "Chat is currently not Muted");
				}
			} else {
				player.sendMessage(ChatColor.RED
						+ "You do Not have Permission to do this Command");
			}
		}

		if (cmd.getName().equalsIgnoreCase("clearchat")
				|| cmd.getName().equalsIgnoreCase("cc")) {
			if (player.hasPermission("ichat.chat.clear")) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (!p.hasPermission("ichat.chat.clear.see")) {
						for (int i = 0; i < 100; i++) {
							p.sendMessage(clear);

						}
						p.sendMessage(ChatColor.RED + sender.getName()
								+ " Has cleared the Chat");
					} else {
						p.sendMessage(ChatColor.RED
								+ "Chat has been cleared for all regular players.");
					}
				}
			} else {
				player.sendMessage(ChatColor.RED
						+ "You do not have permission to do this command!");
			}
		}

		
		if(cmd.getName().equalsIgnoreCase("slowchat")) {
			if(player.hasPermission("ichat.chat.slow")) {
				
				if(args.length < 1) {
					if(slowChat == true) {
						slowChat = false;
						Bukkit.broadcastMessage(ChatColor.GREEN + "Chat is back to regular speed");
					} else {
						player.sendMessage(ChatColor.RED + "/slowchat <seconds>");
						return true;
					}
					return true;
				}
				
				
				if(slowChat == false) {
					
					try {
						seconds = Long.parseLong(args[0]);
						slowChat = true;
					} catch (NumberFormatException e){
						player.sendMessage(ChatColor.RED + "Must be an number");
						return true;
					}
					
					Bukkit.broadcastMessage(ChatColor.RED + "Chat has been slowed for " + args[0] + " seconds");
				}
				
				
			}

		}
		
		return true;
	}
}
