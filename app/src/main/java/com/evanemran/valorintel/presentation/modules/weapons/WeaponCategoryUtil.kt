package com.evanemran.valorintel.presentation.modules.weapons

import com.evanemran.valorintel.domain.models.Weapon

object WeaponCategoryUtil {
    const val ALL = "ALL"

    private val displayOrder = listOf(
        "Sidearm",
        "SMG",
        "Rifle",
        "Shotgun",
        "Sniper",
        "Heavy",
        "Melee",
    )

    fun String?.toDisplayCategory(): String? =
        this?.substringAfter("::")?.replace(Regex("([a-z])([A-Z])"), "$1 $2")

    fun extractCategories(weapons: List<Weapon>): List<String> {
        val present = weapons.mapNotNull { it.category.toDisplayCategory() }.toSet()
        return displayOrder.filter { present.contains(it) }
    }

    fun filterByCategory(weapons: List<Weapon>, category: String): List<Weapon> {
        if (category == ALL) return weapons
        return weapons.filter { it.category.toDisplayCategory() == category }
    }
}
