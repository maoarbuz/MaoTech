package me.maottechnologies;

import me.maottechnologies.cmd.MTTechCommand;
import me.maottechnologies.cmd.ToolUpgradeCommand;
import me.maottechnologies.gui.ToolUpgradeMenu;
import me.maottechnologies.listener.tools.*;
import me.maottechnologies.listener.TownCreationListener;
import me.maottechnologies.listener.TownDeletionListener;
import me.maottechnologies.manager.TownResearchManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Town;

public class MaoTech extends JavaPlugin {

    private TownResearchManager researchManager;
    private ToolUpgradeMenu toolUpgradeMenu;


    @Override
    public void onEnable() {
        researchManager = new TownResearchManager(this);
        researchManager.initializeTownResearches();
        toolUpgradeMenu = new ToolUpgradeMenu(this);
        getServer().getPluginManager().registerEvents(toolUpgradeMenu, this);
        getServer().getPluginManager().registerEvents(new IronToolsListener(this), this);
        getServer().getPluginManager().registerEvents(new StoneToolsListener(this), this);
        getServer().getPluginManager().registerEvents(new GoldToolsListener(this), this);
        getServer().getPluginManager().registerEvents(new DiamondToolsListener(this), this);
        getServer().getPluginManager().registerEvents(new NetheriteToolsListener(this), this);
        getServer().getPluginManager().registerEvents(new TownCreationListener(researchManager), this);
        getServer().getPluginManager().registerEvents(new TownDeletionListener(researchManager), this);
        this.getCommand("mttech").setExecutor(new MTTechCommand(researchManager));
        this.getCommand("technologies").setExecutor(new ToolUpgradeCommand(toolUpgradeMenu));
        getLogger().info("MaoTech plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MaoTech plugin disabled!");
    }

    public boolean canCraftTools(Player player, String tool) {
        Town town = TownyAPI.getInstance().getResident(player.getName()).getTownOrNull();
        return town != null && researchManager.hasResearch(town.getName(), tool);
    }
}