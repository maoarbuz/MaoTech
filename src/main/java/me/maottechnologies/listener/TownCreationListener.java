package me.maottechnologies.listener;

import com.palmergames.bukkit.towny.event.NewTownEvent;
import me.maottechnologies.manager.TownResearchManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownCreationListener implements Listener {

    private TownResearchManager researchManager;

    public TownCreationListener(TownResearchManager researchManager) {
        this.researchManager = researchManager;
    }

    @EventHandler
    public void onTownCreate(NewTownEvent event) {
        String townName = event.getTown().getName();
        researchManager.addTown(townName);
    }
}
