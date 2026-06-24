package com.evanemran.valorintel.domain.models

data class ContentTier(
    val uuid: String? = null,
    val displayName: String? = null,
    val devName: String? = null,
    val rank: Int? = null,
    val juiceValue: Int? = null,
    val juiceCost: Int? = null,
    val highlightColor: String? = null,
    val displayIcon: String? = null,
    val assetPath: String? = null
)
