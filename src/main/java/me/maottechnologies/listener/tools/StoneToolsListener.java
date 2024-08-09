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

public class StoneToolsListener implements Listener {

    private MaoTech plugin;
    private Set<Player> notifiedPlayers;

    public StoneToolsListener(MaoTech plugin) {
        this.plugin = plugin;
        this.notifiedPlayers = new HashSet<>();
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        ItemStack result = inventory.getResult();
        if (result != null && isStoneTool(result.getType())) {
            Player player = (Player) event.getView().getPlayer();
            String tool = "stone_tools";
            if (!plugin.canCraftTools(player, tool)) {
                inventory.setResult(new ItemStack(Material.AIR));
                if (!notifiedPlayers.contains(player)) {
                    player.sendMessage("Your city does not have access to stone toolcrafting!");
                    notifiedPlayers.add(player);
                }
            } else {
                notifiedPlayers.remove(player);
            }
        }
    }

    private boolean isStoneTool(Material material) {
        return material == Material.STONE_PICKAXE ||
                material == Material.STONE_PICKAXE ||
                material == Material.STONE_HOE ||
                material == Material.STONE_SWORD ||
                material == Material.STONE_SHOVEL;
    }
}