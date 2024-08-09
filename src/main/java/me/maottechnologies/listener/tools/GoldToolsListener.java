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

public class GoldToolsListener implements Listener {

    private MaoTech plugin;
    private Set<Player> notifiedPlayers;

    public GoldToolsListener(MaoTech plugin) {
        this.plugin = plugin;
        this.notifiedPlayers = new HashSet<>();
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        ItemStack result = inventory.getResult();
        if (result != null && isGoldTool(result.getType())) {
            Player player = (Player) event.getView().getPlayer();
            String tool = "golden_tools";
            if (!plugin.canCraftTools(player, tool)) {
                inventory.setResult(new ItemStack(Material.AIR));
                if (!notifiedPlayers.contains(player)) {
                    player.sendMessage("Your city does not have access to golden toolcrafting!");
                    notifiedPlayers.add(player);
                }
            } else {
                notifiedPlayers.remove(player);
            }
        }
    }

    private boolean isGoldTool(Material material) {
        return material == Material.GOLDEN_PICKAXE ||
                material == Material.GOLDEN_AXE ||
                material == Material.GOLDEN_HOE ||
                material == Material.GOLDEN_SWORD ||
                material == Material.GOLDEN_SHOVEL;
    }
}