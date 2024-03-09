package de.raphaelgoetz.bettermodels.manager

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ModelManager {

    private var data: MutableMap<String, Int> = hashMapOf()
    var models: MutableList<ItemStack> = mutableListOf()

    private fun reload() {
        models.clear()
        data.clear()

        readConfig()
    }

    fun addModel(name: String, id: Int) {
        //TODO: ADD MODEL INTO JSON
        reload()
    }

    fun removeModel(name: String) {
        //TODO: REMOVE MODEL FROM JSON
        reload()
    }

    fun getModel(name: String): ItemStack {

        //TODO: GET MODEL FORM NAME
        return ItemStack(Material.MAGMA_CREAM)
    }

    fun idAlreadyInUse(int: Int): Boolean {
        return data.containsValue(int)
    }

    fun nameAlreadyInUse(name: String): Boolean {
        return data.containsKey(name)
    }

    private fun readConfig() {
        //TODO: JSON WITH A NAME AS KEY AND A ID AS VALUE --> BUILD ITEM WITH NAME AS DISPLAY AND CUSTOMMODELDATA AS ID
    }
}