package com.evanemran.valorintel.domain.models

data class Event(
    val uuid: String? = null,
    val displayName: String? = null,
    val shortDisplayName: String? = null,
    val startTime: String? = null,
    val endTime: String? = null,
    val assetPath: String? = null
)
