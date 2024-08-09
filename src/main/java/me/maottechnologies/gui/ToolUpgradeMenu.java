package me.maottechnologies.gui;

import com.palmergames.bukkit.towny.TownyAPI;
import me.maottechnologies.MaoTech;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class ToolUpgradeMenu implements Listener {

    private final MaoTech plugin;
    private final Inventory menu;

    public ToolUpgradeMenu(MaoTech plugin) {
        this.plugin = plugin;
        this.menu = Bukkit.createInventory(null, 9, "Upgrade Tools");

        initializeItems();
    }

    private void initializeItems() {
        menu.setItem(2, createMenuItem(Material.STONE_SWORD, "§7Upgrade Stone Tools",
                "§f128 Cobblestone", "§f64 Stone"));
        menu.setItem(3, createMenuItem(Material.IRON_SWORD, "§7Upgrade §fIron §7Tools",
                "§f64 Cobblestone", "§f64 Stone", "§f32 Iron Ingot"));
        menu.setItem(4, createMenuItem(Material.GOLDEN_SWORD, "§7Upgrade §eGolden §7Tools",
                "§f64 Cobblestone", "§f64 Stone", "§f20 Gold Ingot"));
        menu.setItem(5, createMenuItem(Material.DIAMOND_SWORD, "§7Upgrade §bDiamond §7Tools",
                "§f64 Cobblestone", "§f64 Stone", "§f10 Diamond Ingot"));
        menu.setItem(6, createMenuItem(Material.NETHERITE_SWORD, "§7Upgrade §8Netherite §7Tools",
                "§f64 Cobblestone", "§f64 Stone", "§f1 Netherite Ingot"));
    }

    private ItemStack createMenuItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(Arrays.asList(lore));
            item.setItemMeta(meta);
        }
        return item;
    }

    public void openMenu(Player player) {
        player.openInventory(menu);
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Upgrade Tools")) {
            return;
        }

        event.setCancelled(true);

        if (event.getClickedInventory() == null) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || !clickedItem.hasItemMeta()) {
            player.sendMessage("No valid item clicked.");
            return;
        }

        String itemName = clickedItem.getItemMeta().getDisplayName();

        switch (itemName) {
            case "§7Upgrade Stone Tools":
                processUpgrade(player, "stone_tools",
                        new ItemStack(Material.COBBLESTONE, 128), new ItemStack(Material.STONE, 64));
                break;
            case "§7Upgrade §fIron §7Tools":
                processUpgrade(player, "iron_tools",
                        new ItemStack(Material.COBBLESTONE, 64), new ItemStack(Material.STONE, 64),
                        new ItemStack(Material.IRON_INGOT, 32));
                break;
            case "§7Upgrade §eGolden §7Tools":
                processUpgrade(player, "golden_tools",
                        new ItemStack(Material.COBBLESTONE, 64), new ItemStack(Material.STONE, 64),
                        new ItemStack(Material.GOLD_INGOT, 20));
                break;
            case "§7Upgrade §bDiamond §7Tools":
                processUpgrade(player, "diamond_tools",
                        new ItemStack(Material.COBBLESTONE, 64), new ItemStack(Material.STONE, 64),
                        new ItemStack(Material.DIAMOND, 10));
                break;
            case "§7Upgrade §8Netherite §7Tools":
                processUpgrade(player, "netherite_tools",
                        new ItemStack(Material.COBBLESTONE, 64), new ItemStack(Material.STONE, 64),
                        new ItemStack(Material.NETHERITE_INGOT, 1));
                break;
            default:
                player.sendMessage("Invalid item clicked.");
                break;
        }
    }


    private boolean processUpgrade(Player player, String tool, ItemStack... costs) {
        TownyAPI townyAPI = TownyAPI.getInstance();
        UUID playerUUID = player.getUniqueId();
        if (!townyAPI.getResident(playerUUID).hasTown()) {
            player.sendMessage("You're not in the city.");
            return false;
        }

        if (plugin.canCraftTools(player, tool)) {
            player.sendMessage("Your city already has this technology.");
            return false;
        }

        if (hasRequiredItems(player, costs)) {
            removeItems(player, costs);
            player.performCommand("mttech " + tool);
            return true;
        } else {
            player.sendMessage("You don't have the required items for this upgrade.");
            return false;
        }
    }

    private boolean hasRequiredItems(Player player, ItemStack... items) {
        for (ItemStack item : items) {
            if (!player.getInventory().containsAtLeast(item, item.getAmount())) {
                return false;
            }
        }
        return true;
    }

    private void removeItems(Player player, ItemStack... items) {
        for (ItemStack item : items) {
            player.getInventory().removeItem(item);
        }
    }
}
