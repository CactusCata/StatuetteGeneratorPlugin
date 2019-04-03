package fr.cactuscata.statuettegenerator;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.cactuscata.statuettegenerator.commands.AsCommand;
import fr.cactuscata.statuettegenerator.commands.LockCommand;

public class StatuetteGenerator extends JavaPlugin {

	private static Plugin plugin;
	
	@Override
	public void onEnable() {
	
		plugin = this;
		
		LockCommand lock = new LockCommand();
		
		Bukkit.getPluginManager().registerEvents(lock, this);
		
		super.getCommand("lock").setExecutor(lock);
		super.getCommand("as").setExecutor(new AsCommand());
		
	}
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
}
