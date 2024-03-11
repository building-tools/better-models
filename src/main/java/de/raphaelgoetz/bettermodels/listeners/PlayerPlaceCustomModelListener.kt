package de.raphaelgoetz.bettermodels.listeners

import de.raphaelgoetz.bettermodels.BetterModels
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class PlayerPlaceCustomModelListener(private val betterModels: BetterModels) : Listener {

    @EventHandler
    fun onPlayerInteractEvent(playerInteractEvent: PlayerInteractEvent) {
        //TODO: PLACE MODEL ON SOLID BLOCK OR SLAB
    }
}