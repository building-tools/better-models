package de.raphaelgoetz.bettermodels.commands

import de.raphaelgoetz.bettermodels.BetterModels
import de.raphaelgoetz.bettermodels.menu.ModelMenu
import net.axay.kspigot.gui.openGUI
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GetModel(private val betterModels: BetterModels) : CommandExecutor {

    override fun onCommand(player: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (player !is Player) return true
        val menu = ModelMenu(betterModels).menu
        player.openGUI(menu)
        return false
    }
}