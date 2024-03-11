package de.raphaelgoetz.bettermodels

import de.raphaelgoetz.bettermodels.commands.ConfigureModel
import de.raphaelgoetz.bettermodels.commands.EditModel
import de.raphaelgoetz.bettermodels.commands.GetModel
import de.raphaelgoetz.bettermodels.commands.RemoveModel
import de.raphaelgoetz.bettermodels.listeners.PlayerInteractModelListener
import de.raphaelgoetz.bettermodels.listeners.PlayerJoinEditListener
import de.raphaelgoetz.bettermodels.manager.ModelManager
import de.raphaelgoetz.bettermodels.manager.PlayerManager
import net.axay.kspigot.main.KSpigot
import org.bukkit.Bukkit

class BetterModels : KSpigot() {

    val playerManager: PlayerManager = PlayerManager()
    val modelManager: ModelManager = ModelManager()

    override fun startup() {

        Bukkit.getPluginManager().registerEvents(PlayerInteractModelListener(this), this)
        Bukkit.getPluginManager().registerEvents(PlayerJoinEditListener(this), this)

        getCommand("getModel")?.setExecutor(GetModel(this))
        getCommand("editModel")?.setExecutor(EditModel(this))
        getCommand("removeModel")?.setExecutor(RemoveModel())
        getCommand("model")?.setExecutor(ConfigureModel(this))
    }
}