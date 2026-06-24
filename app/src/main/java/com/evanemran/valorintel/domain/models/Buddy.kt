package com.evanemran.valorintel.domain.models

data class Buddy(
    val uuid: String? = null,
    val displayName: String? = null,
    val isHiddenIfNotOwned: Boolean? = null,
    val themeUuid: String? = null,
    val displayIcon: String? = null,
    val assetPath: String? = null,
    val levels: List<BuddyLevel> = emptyList()
)

data class BuddyLevel(
    val uuid: String? = null,
    val charmLevel: Int? = null,
    val hideIfNotOwned: Boolean? = null,
    val displayName: String? = null,
    val displayIcon: String? = null,
    val assetPath: String? = null
)
