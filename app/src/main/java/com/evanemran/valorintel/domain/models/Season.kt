package com.evanemran.valorintel.domain.models

data class Season(
    val uuid: String? = null,
    val displayName: String? = null,
    val title: String? = null,
    val type: String? = null,
    val startTime: String? = null,
    val endTime: String? = null,
    val parentUuid: String? = null,
    val assetPath: String? = null
)

data class CompetitiveSeason(
    val uuid: String? = null,
    val startTime: String? = null,
    val endTime: String? = null,
    val seasonUuid: String? = null,
    val competitiveTiersUuid: String? = null,
    val borders: List<SeasonBorder> = emptyList(),
    val assetPath: String? = null
)

data class SeasonBorder(
    val uuid: String? = null,
    val level: Int? = null,
    val winsRequired: Int? = null,
    val displayIcon: String? = null,
    val smallIcon: String? = null,
    val assetPath: String? = null
)
