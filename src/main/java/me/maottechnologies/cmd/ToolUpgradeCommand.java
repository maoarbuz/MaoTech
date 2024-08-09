package me.maottechnologies.cmd;

import me.maottechnologies.gui.ToolUpgradeMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToolUpgradeCommand implements CommandExecutor {

    private ToolUpgradeMenu toolUpgradeMenu;

    public ToolUpgradeCommand(ToolUpgradeMenu toolUpgradeMenu) {
        this.toolUpgradeMenu = toolUpgradeMenu;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            toolUpgradeMenu.openMenu(player);
            return true;
        } else {
            sender.sendMessage("Only players can use this command.");
            return false;
        }
    }
}
