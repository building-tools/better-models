package de.raphaelgoetz.bettermodels.listeners

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.block.data.type.Slab
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

class PlayerPlaceCustomModelListener : Listener {

    @EventHandler
    fun onPlayerInteractEvent(playerInteractEvent: PlayerInteractEvent) {

        if (playerInteractEvent.hand == EquipmentSlot.OFF_HAND) return
        if (!playerInteractEvent.action.isRightClick) return
        if (playerInteractEvent.clickedBlock == null) return

        val item = playerInteractEvent.item ?: return
        if (item.type != Material.PAPER) return
        if (item.itemMeta.customModelData == 0) return

        val interactLocation = playerInteractEvent.interactionPoint ?: return
        val y = if (interactLocation.block.blockData is Slab) interactLocation.blockY + 0.5 else interactLocation.blockY
        val location = Location(
            interactLocation.world,
            interactLocation.blockX + 0.5,
            y.toDouble() + 0.5,
            interactLocation.blockZ + 0.5
        )

        val itemDisplay: ItemDisplay = location.world.spawn(location, ItemDisplay::class.java)
        itemDisplay.setRotation(getRotation(playerInteractEvent.player), 0.0f)
        itemDisplay.itemStack = item
    }

    private fun getRotation(player: Player): Float {
        return when (player.facing) {
            BlockFace.NORTH -> 180.0f
            BlockFace.EAST -> -90.0f
            BlockFace.WEST -> 90.0f
            else -> 0.0f
        }
    }
}