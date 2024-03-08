package de.raphaelgoetz.bettermodels

import de.raphaelgoetz.bettermodels.manager.ModelManager
import de.raphaelgoetz.bettermodels.manager.PlayerManager
import net.axay.kspigot.main.KSpigot

class BetterModels : KSpigot() {

    val playerManager: PlayerManager = PlayerManager()
    val modelManager: ModelManager = ModelManager()

    override fun startup() {

    }
}