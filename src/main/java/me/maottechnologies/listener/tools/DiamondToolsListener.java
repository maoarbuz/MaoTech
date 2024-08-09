package me.maottechnologies.listener.tools;

import me.maottechnologies.MaoTech;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class DiamondToolsListener implements Listener {

    private MaoTech plugin;
    private Set<Player> notifiedPlayers;

    public DiamondToolsListener(MaoTech plugin) {
        this.plugin = plugin;
        this.notifiedPlayers = new HashSet<>();
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        ItemStack result = inventory.getResult();
        if (result != null && isDiamondTool(result.getType())) {
            Player player = (Player) event.getView().getPlayer();
            String tool = "diamond_tools";
            if (!plugin.canCraftTools(player, tool)) {
                inventory.setResult(new ItemStack(Material.AIR));
                if (!notifiedPlayers.contains(player)) {
                    player.sendMessage("Your city does not have access to diamond toolcrafting!");
                    notifiedPlayers.add(player);
                }
            } else {
                notifiedPlayers.remove(player);
            }
        }
    }

    private boolean isDiamondTool(Material material) {
        return material == Material.DIAMOND_PICKAXE ||
                material == Material.DIAMOND_AXE ||
                material == Material.DIAMOND_HOE ||
                material == Material.DIAMOND_SWORD ||
                material == Material.DIAMOND_SHOVEL;
    }
}