package me.maottechnologies.listener.tools;

import me.maottechnologies.MaoTech;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class NetheriteToolsListener implements Listener {

    private MaoTech plugin;
    private Set<Player> notifiedPlayers;

    public NetheriteToolsListener(MaoTech plugin) {
        this.plugin = plugin;
        this.notifiedPlayers = new HashSet<>();
    }

    @EventHandler
    public void onPrepareSmithing(PrepareSmithingEvent event) {
        ItemStack result = event.getResult();
        if (result != null && isNetheriteTool(result.getType())) {
            Player player = (Player) event.getView().getPlayer();
            String tool = "netherite_tools";
            if (!plugin.canCraftTools(player, tool)) {
                event.setResult(new ItemStack(Material.AIR));
                if (!notifiedPlayers.contains(player)) {
                    player.sendMessage("Your city does not have access to netherite toolcrafting!");
                    notifiedPlayers.add(player);
                }
            } else {
                notifiedPlayers.remove(player);
            }
        }
    }

    private boolean isNetheriteTool(Material material) {
        return material == Material.NETHERITE_PICKAXE ||
                material == Material.NETHERITE_AXE ||
                material == Material.NETHERITE_HOE ||
                material == Material.NETHERITE_SWORD ||
                material == Material.NETHERITE_SHOVEL;
    }
}
