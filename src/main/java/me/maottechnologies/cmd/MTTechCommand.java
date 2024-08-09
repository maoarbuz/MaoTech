package me.maottechnologies.cmd;

import me.maottechnologies.manager.TownResearchManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Town;

public class MTTechCommand implements CommandExecutor {

    private TownResearchManager researchManager;

    public MTTechCommand(TownResearchManager researchManager) {
        this.researchManager = researchManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mttech")) {
            if (args.length == 1) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    Town town = TownyAPI.getInstance().getResident(player.getName()).getTownOrNull();

                    if (town != null) {
                        String researchItem = args[0].toLowerCase();

                        switch (researchItem) {
                            case "iron_tools":
                                researchManager.unlockResearch(town.getName(), "iron_tools");
                                player.sendMessage("Your city can now craft iron tools!");
                                break;
                            case "stone_tools":
                                researchManager.unlockResearch(town.getName(), "stone_tools");
                                player.sendMessage("Your city can now craft stone tools!");
                                break;
                            case "golden_tools":
                                researchManager.unlockResearch(town.getName(), "golden_tools");
                                player.sendMessage("Your city can now craft golden tools!");
                                break;
                            case "diamond_tools":
                                researchManager.unlockResearch(town.getName(), "diamond_tools");
                                player.sendMessage("Your city can now craft diamond tools!");
                                break;
                            case "netherite_tools":
                                researchManager.unlockResearch(town.getName(), "netherite_tools");
                                player.sendMessage("Your city can now craft netherite tools!");
                                break;
                            default:
                                player.sendMessage("Unknown research subject.");
                                break;
                        }
                    } else {
                        player.sendMessage("You're out of town!");
                    }
                    return true;
                }
            } else {
                sender.sendMessage("Usage: /mttech <type_of_tools>");
            }
        }
        return false;
    }
}
