package de.raphaelgoetz.bettermodels.manager

import net.axay.kspigot.chat.literalText
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.UUID

class PlayerManager {

    private val activeEditors: MutableList<UUID> = mutableListOf()
    private val playerInventories: MutableMap<UUID, MutableList<ItemStack>> = mutableMapOf()

    fun isEditor(player: Player): Boolean {
        return activeEditors.contains(player.uniqueId)
    }

    fun toggleEditorInventory(player: Player) {

        if (activeEditors.contains(player.uniqueId)) {
            restoreInventory(player)
            return
        }

        storeInventory(player)
    }

    fun addModelToInv(player: Player, itemStack: ItemStack) {
        if (player.inventory.contents.size >= 53) return

        if (activeEditors.contains(player.uniqueId)) {
            playerInventories[player.uniqueId]?.add(itemStack)
            return
        }

        player.inventory.addItem(itemStack)
    }

    private fun restoreInventory(player: Player) {
        player.inventory.clear()

        if (!playerInventories.contains(player.uniqueId)) return
        val items = playerInventories[player.uniqueId]
        if (items.isNullOrEmpty()) return

        for (item in items) {
            if (item.type == Material.AIR) continue
            player.inventory.addItem(item)
        }

        activeEditors.remove(player.uniqueId)
    }

    private fun storeInventory(player: Player) {
        activeEditors.add(player.uniqueId)
        val inventory = player.inventory.contents.toList().filterNotNull()
        playerInventories[player.uniqueId] = inventory.toMutableList()

        val rotateItem = itemStack(Material.EMERALD) {
            meta {
                name = literalText("Rotate nearest Model") {  }
            }
        }

        val removeItem = itemStack(Material.MAGMA_CREAM) {
            meta {
                name = literalText("Rotate nearest Model") {  }
            }
        }

        val getItem = itemStack(Material.ECHO_SHARD) {
            meta {
                name = literalText("Rotate nearest Model") {  }
            }
        }

        player.closeInventory()
        player.inventory.addItem(rotateItem)
        player.inventory.addItem(removeItem)
        player.inventory.addItem(getItem)
    }
}