package com.dre.brewery.integration.barrel;

import org.bukkit.entity.Player;

import com.dre.brewery.api.events.barrel.BarrelAccessEvent;
import com.plotsquared.bukkit.player.BukkitPlayer;
import com.plotsquared.bukkit.util.BukkitUtil;
import com.plotsquared.core.location.Location;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotArea;

public class PlotSquaredBarrel {

	public static boolean checkAccess(BarrelAccessEvent event) {
		final Location location = BukkitUtil.adapt(event.getSpigot().getLocation());
        final PlotArea area = location.getPlotArea();
        if (area == null) {
            return true;
        }
        final Plot plot = area.getPlot(location);
        if (plot == null) {
            return false;
        }
        final Player player = event.getPlayer();
        if (!plot.hasOwner()) {
            return player.hasPermission("plots.admin.interact.unowned");
        }
        final BukkitPlayer bukkitPlayer = BukkitUtil.adapt(player);
        return plot.isAdded(bukkitPlayer.getUUID()) || player.hasPermission("plots.admin.interact.other");
	}
	
}