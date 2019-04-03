package fr.cactuscata.statuettegenerator.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.EulerAngle;

public class LockCommand implements CommandExecutor, Listener {

	private final Map<ArmorStand, Float> map = new HashMap<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("Il n'y a que les joueurs qui peuvent executer cette commande !");
			return true;
		}

		if (args.length == 0) {
			sender.sendMessage("Veuillez préciser le rayon !");
			return true;
		}

		final float rayon;

		try {
			rayon = Float.parseFloat(args[0]);
		} catch (NumberFormatException e) {
			sender.sendMessage("Veuillez choisir un rayon correct !");
			return true;
		}

		Player player = (Player) sender;

		map.put(setup(
				(ArmorStand) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND),
				player.getName()), rayon);

		// if (this.runnable != null) {
		// this.runnable.cancel();
		// this.runnable = null;
		// sender.sendMessage("Lock désactivé !");
		// return true;
		// }
		sender.sendMessage("Lock activé !");

		return true;
	}

	private ArmorStand setup(ArmorStand as, String playerName) {
		as.setArms(true);
		as.setBasePlate(false);
		as.setGravity(false);
		as.setMarker(true);
		as.setSmall(true);
		as.setRightArmPose(new EulerAngle(340.0d, 0.0d, 0.0d));
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(playerName);
		skull.setItemMeta(meta);
		as.setItemInHand(skull);

		return as;
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Location to = event.getTo();
		this.map.entrySet()
				.forEach(entry -> entry.getKey()
						.teleport(new Location(to.getWorld(),
								to.getX() + (entry.getValue() * Math.cos(Math.toRadians(to.getYaw() + 315))), to.getY(),
								to.getZ() + (entry.getValue() * Math.sin(Math.toRadians(to.getYaw() + 315))),
								to.getYaw(), to.getPitch())));
	}

}
