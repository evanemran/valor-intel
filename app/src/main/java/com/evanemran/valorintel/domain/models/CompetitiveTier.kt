package com.evanemran.valorintel.domain.models

data class CompetitiveTier(
    val uuid: String? = null,
    val assetObjectName: String? = null,
    val tiers: List<Tier> = emptyList(),
    val assetPath: String? = null
)

data class Tier(
    val tier: Int? = null,
    val tierName: String? = null,
    val division: String? = null,
    val divisionName: String? = null,
    val color: String? = null,
    val backgroundColor: String? = null,
    val smallIcon: String? = null,
    val largeIcon: String? = null,
    val rankTriangleDownIcon: String? = null,
    val rankTriangleUpIcon: String? = null
)
