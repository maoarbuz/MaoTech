package me.maottechnologies.listener.tools;

import me.maottechnologies.MaoTech;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class IronToolsListener implements Listener {

    private MaoTech plugin;
    private Set<Player> notifiedPlayers;

    public IronToolsListener(MaoTech plugin) {
        this.plugin = plugin;
        this.notifiedPlayers = new HashSet<>();
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        ItemStack result = inventory.getResult();
        if (result != null && isIronTool(result.getType())) {
            Player player = (Player) event.getView().getPlayer();
            String tool = "iron_tools";
            if (!plugin.canCraftTools(player, tool)) {
                inventory.setResult(new ItemStack(Material.AIR));
                if (!notifiedPlayers.contains(player)) {
                    player.sendMessage("Your city does not have access to iron toolcrafting!");
                    notifiedPlayers.add(player);
                }
            } else {
                notifiedPlayers.remove(player);
            }
        }
    }

    private boolean isIronTool(Material material) {
        return material == Material.IRON_PICKAXE ||
                material == Material.IRON_AXE ||
                material == Material.IRON_HOE ||
                material == Material.IRON_SWORD ||
                material == Material.IRON_SHOVEL;
    }
}