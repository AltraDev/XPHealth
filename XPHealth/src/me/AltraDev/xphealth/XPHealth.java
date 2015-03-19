package me.AltraDev.xphealth;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class XPHealth extends JavaPlugin implements Listener {
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getLogger().info("==== XPHealth has been enabled! ====");
	}
	
	public void onDisable() {
		Bukkit.getServer().getLogger().info("==== XPHealth has been disabled! ====");
	}
	
	public boolean xphEnabled = false;
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Console cannot run this command");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("xphealth")) {
			Player player = (Player) sender;
			
			if(!sender.hasPermission("XPHealth.toggle")) {
				sender.sendMessage(ChatColor.RED + "You are not permitted to use this command!");
				return true;
			}
			
			if (xphEnabled == false) {
				xphEnabled = true;
				player.setHealthScale(player.getLevel() + 20);
				player.setMaxHealth(player.getLevel() + 20.0);
				Bukkit.broadcastMessage(ChatColor.GOLD + "XPHealth has been " + ChatColor.GREEN + "ENABLED");
			}
			else {
				if (xphEnabled == true) {
					xphEnabled = false;
					player.setHealthScale(20);
					player.setMaxHealth(20.0);
					Bukkit.broadcastMessage(ChatColor.GOLD + "XPHealth has been " + ChatColor.RED + "DISABLED");
				}
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("sxphealth")) {
			Player player = (Player) sender;
			
			if(!sender.hasPermission("XPHealth.xptoggle")) {
				sender.sendMessage(ChatColor.RED + "You are not permitted to use this command!");
				return true;
			}
			
			if (xphEnabled == false) {
				xphEnabled = true;
				player.setHealthScale(player.getLevel() + 20);
				player.setMaxHealth(player.getLevel() + 20.0);
				player.sendMessage(ChatColor.GOLD + "XPHealth has been " + ChatColor.GREEN + "ENABLED");
			}
			else {
				if (xphEnabled == true) {
					xphEnabled = false;
					player.setHealthScale(20);
					player.setMaxHealth(20.0);
					player.sendMessage(ChatColor.GOLD + "XPHealth has been " + ChatColor.RED + "DISABLED");
				}
			}
		}
		
		return true;
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (xphEnabled == true) {
			player.setHealthScale(player.getLevel() + 20);
			player.setMaxHealth(player.getLevel() + 20.0);
		} else {
			if (xphEnabled == false) {
				player.setHealthScale(20);
				player.setMaxHealth(20.0);
			}
		}
	}
	@EventHandler
	public void onLevel(PlayerLevelChangeEvent event) {
		Player player = event.getPlayer();
		if (xphEnabled == true) {
			player.setHealthScale(player.getLevel() + 20);
			player.setMaxHealth(player.getLevel() + 20.0);
		} else {
			if (xphEnabled == false) {
				player.setHealthScale(20);
				player.setMaxHealth(20.0);
			}
		}
	}
}