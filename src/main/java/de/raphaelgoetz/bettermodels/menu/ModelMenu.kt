package de.raphaelgoetz.bettermodels.menu

import de.raphaelgoetz.bettermodels.BetterModels
import net.axay.kspigot.gui.GUIType
import net.axay.kspigot.gui.PageChangeEffect
import net.axay.kspigot.gui.Slots
import net.axay.kspigot.gui.kSpigotGUI
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ModelMenu(private val betterModels: BetterModels) {

    val menu = kSpigotGUI(GUIType.SIX_BY_NINE) {

        page(1) {

            val modelCompound = createRectCompound<ItemStack>(
                Slots.RowTwoSlotOne, Slots.RowSixSlotNine,
                iconGenerator = { it },
                onClick = {
                    clickEvent, element ->
                    clickEvent.player.inventory.addItem(element)
                    clickEvent.bukkitEvent.isCancelled = true
                }
            )

            modelCompound.addContent(betterModels.modelManager.models)

            transitionFrom = PageChangeEffect.SWIPE_VERTICALLY
            transitionTo = PageChangeEffect.SWIPE_VERTICALLY

            compoundScroll(
                Slots.RowOneSlotNine,
                ItemStack(Material.ARROW), modelCompound, scrollTimes = 6
            )
            compoundScroll(
                Slots.RowOneSlotOne,
                ItemStack(Material.ARROW), modelCompound, scrollTimes = 6, reverse = true
            )
        }
    }
}