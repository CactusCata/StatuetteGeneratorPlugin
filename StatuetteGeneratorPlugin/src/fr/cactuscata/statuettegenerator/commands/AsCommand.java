package fr.cactuscata.statuettegenerator.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("Il n'y a que les joueurs qui peuvent executer cette commande !");
			return true;
		}

		// Player player = (Player) sender;
		// Location loc = player.getLocation();

		// double x = loc.getX();
		// double y = loc.getY();
		// double z = loc.getZ();

		return true;
	}

}
