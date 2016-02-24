package me.iran.bukkit;



import org.bukkit.Bukkit;

//import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.util.Seekable.File;
import org.bukkit.plugin.java.JavaPlugin;

public class iChat extends JavaPlugin {

	static boolean isLocked = false;
		
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new ChatListeners(this), this);
		getCommand("clearchat").setExecutor(new ChatCommands(this));
		getCommand("cc").setExecutor(new ChatCommands(this));
		getCommand("lockchat").setExecutor(new ChatCommands(this));
		getCommand("lc").setExecutor(new ChatCommands(this));
		getCommand("unlock").setExecutor(new ChatCommands(this));
		getCommand("ul").setExecutor(new ChatCommands(this));
		getCommand("slowchat").setExecutor(new ChatCommands(this));
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	//learn how to add new config.
	//Add custom messages to config
}
