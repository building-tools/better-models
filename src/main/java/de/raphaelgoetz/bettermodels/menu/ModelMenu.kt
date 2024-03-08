package de.raphaelgoetz.bettermodels.menu

import de.raphaelgoetz.bettermodels.BetterModels
import net.axay.kspigot.gui.GUIType
import net.axay.kspigot.gui.kSpigotGUI

class ModelMenu(
    private val betterModels: BetterModels
) {

    val menu = kSpigotGUI(GUIType.SIX_BY_NINE) {

        //TODO: LIST OF EVERY MODEL FROM THE JSON

        page(1) {}
    }
}