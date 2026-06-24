package com.evanemran.valorintel.domain.models

data class Spray(
    val uuid: String? = null,
    val displayName: String? = null,
    val category: String? = null,
    val themeUuid: String? = null,
    val isNullSpray: Boolean? = null,
    val hideIfNotOwned: Boolean? = null,
    val displayIcon: String? = null,
    val fullIcon: String? = null,
    val fullTransparentIcon: String? = null,
    val animationPng: String? = null,
    val animationGif: String? = null,
    val assetPath: String? = null,
    val levels: List<SprayLevel> = emptyList()
)

data class SprayLevel(
    val uuid: String? = null,
    val sprayLevel: Int? = null,
    val displayName: String? = null,
    val displayIcon: String? = null,
    val assetPath: String? = null
)
