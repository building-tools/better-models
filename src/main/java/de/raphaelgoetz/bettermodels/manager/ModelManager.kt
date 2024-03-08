package de.raphaelgoetz.bettermodels.manager

import org.bukkit.inventory.ItemStack

class ModelManager {

    private var models: MutableList<ItemStack> = readConfig()

    fun reload() {
        models.clear()
        models = readConfig()
    }

    fun idAlreadyInUse(int: Int): Boolean {
        return true
    }

    private fun readConfig(): MutableList<ItemStack> {
        val models = mutableListOf<ItemStack>()

        //TODO: JSON WITH A NAME AS KEY AND A ID AS VALUE --> BUILD ITEM WITH NAME AS DISPLAY AND CUSTOMMODELDATA AS ID
        return models
    }
}