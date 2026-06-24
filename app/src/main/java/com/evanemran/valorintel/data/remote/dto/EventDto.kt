package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.Event
import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("shortDisplayName") val shortDisplayName: String? = null,
    @SerializedName("startTime") val startTime: String? = null,
    @SerializedName("endTime") val endTime: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun EventDto.toDomain(): Event = Event(
    uuid = uuid,
    displayName = displayName,
    shortDisplayName = shortDisplayName,
    startTime = startTime,
    endTime = endTime,
    assetPath = assetPath
)
