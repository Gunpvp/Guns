package guns.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import guns.weapons.Gun;
import guns.weapons.GunMaster;
import guns.weopons.data.GunData;

public class GunsCommand extends Command {

	protected GunsCommand() {
		super("guns");
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String name, String[] args) {
		if (cmd.getName().equals("guns")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length>1) {
					if (args[0].toLowerCase().equals("give")) {
						if (args.length>1) {
							GunData data = GunMaster.getGunData(args[1]);
							if (data!= null) {
								Gun gun = new Gun(data);
								p.getInventory().addItem(gun.getItem());
								p.updateInventory();
								data.getItemdata().getSound().play(p);
								p.sendMessage("§8[§eGuns§8] §7Waffe §e"+args[1] + "§7 erhalten!");
							} else {
								p.sendMessage("§8[§eGuns§8] §cEs gibt keine Waffe mit diesen Namen: §e"+args[1]);
							}
						} else {
							p.sendMessage("§8[§eGuns§8] §c/guns give 'name'");
						}
					}
				}
			}
		}
		return false;
	}

}
