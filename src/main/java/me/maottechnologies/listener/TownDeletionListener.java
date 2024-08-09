package me.maottechnologies.listener;

import com.palmergames.bukkit.towny.event.DeleteTownEvent;
import me.maottechnologies.manager.TownResearchManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownDeletionListener implements Listener {

    private TownResearchManager researchManager;

    public TownDeletionListener(TownResearchManager researchManager) {
        this.researchManager = researchManager;
    }

    @EventHandler
    public void onTownDelete(DeleteTownEvent event) {
        String townName = event.getTownName();
        researchManager.removeTown(townName);
    }
}
