package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.Ceremony
import com.google.gson.annotations.SerializedName

data class CeremonyDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun CeremonyDto.toDomain(): Ceremony = Ceremony(
    uuid = uuid,
    displayName = displayName,
    assetPath = assetPath
)
