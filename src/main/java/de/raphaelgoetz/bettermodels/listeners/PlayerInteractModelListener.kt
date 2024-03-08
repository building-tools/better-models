package de.raphaelgoetz.bettermodels.listeners

import de.raphaelgoetz.bettermodels.BetterModels
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class PlayerInteractModelListener(private val betterModels: BetterModels) : Listener {

    @EventHandler
    fun onPlayerInteractEvent(playerInteractEvent: PlayerInteractEvent) {
        val player = playerInteractEvent.player
        if (betterModels.playerManager.isEditor(player)) return
        if (!playerInteractEvent.action.isRightClick) return
        val item = playerInteractEvent.item ?: return

        if (item.type == Material.EMERALD) {
            playerInteractEvent.isCancelled = true
            rotateItemInteraction(player)
            return
        }

        if (item.type == Material.MAGMA_CREAM) {
            playerInteractEvent.isCancelled = true
            removeItemInteraction(player)
            return
        }

        if (item.type == Material.ECHO_SHARD) {
            playerInteractEvent.isCancelled = true
            addItemInteraction(player)
        }
    }

    private fun rotateItemInteraction(player: Player) {
        val entities = player.getNearbyEntities(0.5, 0.0, 0.5)

        for (entity in entities) {
            if (entity.type != EntityType.ITEM_DISPLAY) continue
            player.sendActionBar("§aRotated item-display")
            val yaw = if (entity.location.yaw == 180.0f) -135.0f else entity.location.yaw + 45.0f
            val location = entity.location

            //TODO: IS TELEPORTING ON COORDINATES RLY IMPORTANT?
            entity.teleport(
                Location(
                    entity.world,
                    location.blockX.toDouble() + 0.5,
                    location.blockY.toDouble() + 0.5,
                    location.blockZ.toDouble() + 0.5,
                    yaw,
                    0.0f
                )
            )
        }
    }

    private fun removeItemInteraction(player: Player) {
        val entities = player.getNearbyEntities(0.5, 0.0, 0.5)
        for (entity in entities) {
            if (entity.type != EntityType.ITEM_DISPLAY) continue
            entity.remove()
        }
        player.sendActionBar("§cRemoved item-display")
    }

    private fun addItemInteraction(player: Player) {
        val entities = player.getNearbyEntities(0.5, 0.0, 0.5)
        for (entity in entities) {
            if (entity.type != EntityType.ITEM_DISPLAY) continue
            val display = entity as ItemDisplay
            val model = display.itemStack ?: return
            player.sendActionBar("§3You will receive the model if you left the edit mode")
            betterModels.playerManager.addModelToInv(player, model)
        }
    }
}
