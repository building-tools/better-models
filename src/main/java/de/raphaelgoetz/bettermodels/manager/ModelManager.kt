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

    var data: MutableMap<String, Int> = hashMapOf()
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
        val json = JsonParser.parseReader(FileReader(getConfig())).asJsonObject
        json.addProperty(name, id)
        getConfig().writeText(json.toString())

        Bukkit.broadcast(
            literalText("Added new model: $name with customModelData: $id") {
                color = KColors.LIMEGREEN
                italic = false
            }
        )

        reload()
    }

    fun removeModel(name: String) {
        val json = JsonParser.parseReader(FileReader(getConfig())).asJsonObject
        json.remove(name)

        Bukkit.broadcast(
            literalText("Removed the model: $name") {
                color = KColors.LIMEGREEN
                italic = false
            }
        )

        getConfig().writeText(json.toString())
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

    private fun getConfig(): File {
        val folderPath = Bukkit.getPluginsFolder().path + "/BetterModels"
        val folder = File(folderPath)
        if (!folder.exists()) folder.mkdirs()

        val filePath = ("$folderPath/model.json")
        val jsonFile = File(filePath)

        if (!jsonFile.exists()) {
            val json = JsonParser.parseReader(FileReader(getConfig())).asJsonObject
            json.addProperty("{}", "")
            jsonFile.writeText(json.toString())
            return jsonFile
        }

        return jsonFile
    }

    private fun readConfig() {
        val json = JsonParser.parseReader(FileReader(getConfig())).asJsonObject

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