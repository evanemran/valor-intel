package com.evanemran.valorintel.domain.models

data class PlayerCard(
    val uuid: String? = null,
    val displayName: String? = null,
    val isHiddenIfNotOwned: Boolean? = null,
    val themeUuid: String? = null,
    val displayIcon: String? = null,
    val smallArt: String? = null,
    val wideArt: String? = null,
    val largeArt: String? = null,
    val assetPath: String? = null
)
