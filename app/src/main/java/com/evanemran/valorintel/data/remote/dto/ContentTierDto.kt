package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.ContentTier
import com.google.gson.annotations.SerializedName

data class ContentTierDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("devName") val devName: String? = null,
    @SerializedName("rank") val rank: Int? = null,
    @SerializedName("juiceValue") val juiceValue: Int? = null,
    @SerializedName("juiceCost") val juiceCost: Int? = null,
    @SerializedName("highlightColor") val highlightColor: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun ContentTierDto.toDomain(): ContentTier = ContentTier(
    uuid = uuid,
    displayName = displayName,
    devName = devName,
    rank = rank,
    juiceValue = juiceValue,
    juiceCost = juiceCost,
    highlightColor = highlightColor,
    displayIcon = displayIcon,
    assetPath = assetPath
)
