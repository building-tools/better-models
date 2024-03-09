package de.raphaelgoetz.bettermodels.commands

import de.raphaelgoetz.bettermodels.BetterModels
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class EditModel(private val betterModels: BetterModels) : CommandExecutor {

    override fun onCommand(player: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (player !is Player) return true
        betterModels.playerManager.toggleEditorInventory(player)
        return false
    }
}