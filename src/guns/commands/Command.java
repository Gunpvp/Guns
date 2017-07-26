package guns.commands;

import org.bukkit.command.CommandExecutor;

import guns.Guns;

public abstract class Command implements CommandExecutor {
	
	protected Command(String cmd) {
		Guns.getPlugin().getCommand(cmd).setExecutor(this);
	}
	
}
