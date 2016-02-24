package me.iran.bukkit;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListeners implements Listener {
	iChat plugin;
	static HashMap<UUID, Long> inSlowChat = new HashMap<UUID, Long>();
	public ChatListeners(iChat plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onType(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		if (iChat.isLocked != false
				&& !event.getPlayer().hasPermission("ichat.chat.bypass")) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(
					player.getDisplayName() + ": " + event.getMessage());
		}
 
		String message = event.getMessage().toString().toLowerCase().replace(" ", "");
		List<String> cursewords = plugin.getConfig().getStringList("filtered");
		
		for(String badword : cursewords) {
			badword = badword.toString().toLowerCase();
			
			if(message.contains(badword)) {
				
				event.setCancelled(true);
				for (Player canSee : Bukkit.getOnlinePlayers()) {
					if (canSee.hasPermission("ichat.filter.see")) {
						String old = event.getMessage();
						canSee.sendMessage(ChatColor.RED + "[Filter] "
								+ ChatColor.YELLOW + "(" + player.getName()
								+ "): " + old);
					}
			}
		}
		
	}
		
		if(ChatCommands.slowChat == true) {
			if(player.hasPermission("ichat.chat.slow.bypass")) {
				return;
			} else {
			if(inSlowChat.containsKey(player.getUniqueId()) && inSlowChat.get(player.getUniqueId()) > System.currentTimeMillis()) {
				long timeLeft = inSlowChat.get(player.getUniqueId()) - System.currentTimeMillis();
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You can speak in " + timeLeft/1000 + " seconds");
			} else {
				inSlowChat.put(player.getUniqueId(), System.currentTimeMillis() + (ChatCommands.seconds * 1000));
			}
		}
	  }
	}
}
