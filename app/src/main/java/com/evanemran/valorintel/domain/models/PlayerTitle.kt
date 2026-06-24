package com.evanemran.valorintel.domain.models

data class PlayerTitle(
    val uuid: String? = null,
    val displayName: String? = null,
    val titleText: String? = null,
    val isHiddenIfNotOwned: Boolean? = null,
    val assetPath: String? = null
)
