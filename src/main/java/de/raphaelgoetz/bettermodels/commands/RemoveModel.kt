package de.raphaelgoetz.bettermodels.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class RemoveModel : CommandExecutor {

    override fun onCommand(player: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (player !is Player) return true

        val entities = player.getNearbyEntities(0.5, 0.0, 0.5)
        for (entity in entities) {
            if (entity.type != EntityType.ITEM_DISPLAY) continue
            entity.remove()
        }

        player.sendActionBar("§cRemoved item-display")
        return false
    }
}