package com.evanemran.valorintel.domain.models

data class Bundle(
    val uuid: String? = null,
    val displayName: String? = null,
    val displayNameSubText: String? = null,
    val description: String? = null,
    val extraDescription: String? = null,
    val promoDescription: String? = null,
    val useAdditionalContext: Boolean? = null,
    val displayIcon: String? = null,
    val displayIcon2: String? = null,
    val displayIcon3: String? = null,
    val logoIcon: String? = null,
    val verticalPromoImage: String? = null,
    val assetPath: String? = null
)
