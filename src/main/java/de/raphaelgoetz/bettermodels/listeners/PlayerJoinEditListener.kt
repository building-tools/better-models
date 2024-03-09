package de.raphaelgoetz.bettermodels.listeners

import de.raphaelgoetz.bettermodels.BetterModels
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class PlayerJoinEditListener(private val betterModels: BetterModels) : Listener {

    @EventHandler
    fun onPlayerInteractEvent(playerInteractEvent: PlayerInteractEvent) {
        if (playerInteractEvent.action != Action.RIGHT_CLICK_AIR) return
        val player = playerInteractEvent.player

        if (!player.isSneaking) return
        if (playerInteractEvent.item != null) return

        betterModels.playerManager.toggleEditorInventory(player)
        playerInteractEvent.isCancelled = true
    }
}