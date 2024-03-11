package de.raphaelgoetz.bettermodels.manager

import com.google.gson.JsonParser
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.io.File
import java.io.FileReader

class ModelManager {

    private var data: MutableMap<String, Int> = hashMapOf()
    var models: MutableList<ItemStack> = mutableListOf()

    init {
        reload()
    }

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
        val customModelData = data[name] ?: return ItemStack(Material.AIR)

        return itemStack(Material.PAPER) {
            meta {
                setCustomModelData(customModelData)
                displayName(literalText(name) {
                    color = KColors.RED
                    italic = false
                    bold = true
                })
            }
        }
    }

    fun idAlreadyInUse(int: Int): Boolean {
        return data.containsValue(int)
    }

    fun nameAlreadyInUse(name: String): Boolean {
        return data.containsKey(name)
    }

    private fun readConfig() {
        val folderPath = Bukkit.getPluginsFolder().path + "/BetterModels"
        val folder = File(folderPath)
        if (!folder.exists()) folder.mkdirs()

        val filePath = ("$folderPath/model.json")
        val jsonFile = File(filePath)

        if (!jsonFile.exists()) {
            //TODO: AUTOGENERATE JSON
            return
        }

        val json = JsonParser.parseReader(FileReader(jsonFile)).asJsonObject

        json.entrySet().forEach {
            println(it.key)
            data.putIfAbsent(it.key, it.value.asInt)
            val item = itemStack(Material.PAPER) {
                meta {
                    setCustomModelData(it.value.asInt)
                    name = literalText(it.key) {
                        color = KColors.LIMEGREEN
                    }
                }
            }

            models.add(item)
        }
    }
}