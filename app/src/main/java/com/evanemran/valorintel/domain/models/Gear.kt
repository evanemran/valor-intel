package com.evanemran.valorintel.domain.models

data class Gear(
    val uuid: String? = null,
    val displayName: String? = null,
    val description: String? = null,
    val descriptions: List<String> = emptyList(),
    val details: List<GearDetail> = emptyList(),
    val displayIcon: String? = null,
    val assetPath: String? = null,
    val shopData: ShopData? = null
)

data class GearDetail(
    val name: String? = null,
    val value: String? = null
)
