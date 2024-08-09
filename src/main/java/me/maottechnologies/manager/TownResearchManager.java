package me.maottechnologies.manager;

import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import com.palmergames.bukkit.towny.TownyUniverse;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TownResearchManager {

    private File configFile;
    private FileConfiguration config;
    private Map<String, Map<String, Boolean>> townResearches = new HashMap<>();

    public TownResearchManager(JavaPlugin plugin) {
        configFile = new File(plugin.getDataFolder(), "researches.yml");
        if (!configFile.exists()) {
            try {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);
        loadResearches();
    }

    private void loadResearches() {
        for (String townName : config.getKeys(false)) {
            Map<String, Boolean> researches = new HashMap<>();
            for (String research : config.getConfigurationSection(townName).getKeys(false)) {
                researches.put(research, config.getBoolean(townName + "." + research));
            }
            townResearches.put(townName, researches);
        }
    }

    private void saveResearches() {
        for (String townName : townResearches.keySet()) {
            for (String research : townResearches.get(townName).keySet()) {
                config.set(townName + "." + research, townResearches.get(townName).get(research));
            }
        }
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unlockResearch(String townName, String research) {
        townResearches.computeIfAbsent(townName, k -> new HashMap<>()).put(research, true);
        saveResearches();
    }

    public boolean hasResearch(String townName, String research) {
        return townResearches.containsKey(townName) && townResearches.get(townName).getOrDefault(research, false);
    }

    public void initializeTownResearches() {
        for (Town town : TownyUniverse.getInstance().getTowns()) {
            if (!townResearches.containsKey(town.getName())) {
                townResearches.put(town.getName(), new HashMap<>());
                config.createSection(town.getName());
            }
        }
        saveResearches();
    }

    public void addTown(String townName) {
        if (!townResearches.containsKey(townName)) {
            townResearches.put(townName, new HashMap<>());
            config.createSection(townName);
            saveResearches();
        }
    }

    public void removeTown(String townName) {
        if (townResearches.containsKey(townName)) {
            townResearches.remove(townName);
            config.set(townName, null);
            saveResearches();
        }
    }

}
