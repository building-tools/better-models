package de.raphaelgoetz.bettermodels.commands

import de.raphaelgoetz.bettermodels.BetterModels
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ConfigureModel(private val betterModels: BetterModels) : CommandExecutor {

    override fun onCommand(player: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (player !is Player) return true
        val arguments = p3 ?: return true

        if (arguments.size <= 1) return true
        val subCommand = arguments[0]
        val modelName = arguments[1]

        if (subCommand == "add") {
            addModel(arguments, player)
            return false
        }

        if (subCommand == "remove") {
            removeModel(modelName, player)
            return false
        }

        if (subCommand == "get") {
            getModel(modelName, player)
            return false
        }

        return false
    }

    private fun addModel(arguments: Array<out String>, player: Player) {
        if (arguments.size <= 2) return

        try {

            val modelName = arguments[1]
            val id = arguments[2].toInt()

            if (betterModels.modelManager.nameAlreadyInUse(modelName)) {
                player.sendMessage("Model-Name is already in use!")
                return
            }


            if (betterModels.modelManager.idAlreadyInUse(id)) {
                player.sendMessage("CustomModelData is already in use!")
                return
            }

            betterModels.modelManager.addModel(modelName, id)
        } catch (ignored: NumberFormatException) {
            player.sendMessage("The given CustomModelData was not a number")
        }
    }


    private fun removeModel(name: String, player: Player) {
        if (!betterModels.modelManager.nameAlreadyInUse(name)) {
            player.sendMessage("Model $name doesn't exists!")
            return
        }

        betterModels.modelManager.removeModel(name)
    }

    private fun getModel(name: String, player: Player) {

        if (!betterModels.modelManager.nameAlreadyInUse(name)) {
            player.sendMessage("Model $name doesn't exists!")
            return
        }

        val model = betterModels.modelManager.getModel(name)
        player.inventory.addItem(model)
    }

    //TODO: ADD TABCOMPLETE TO LIST ALL MODELS
}