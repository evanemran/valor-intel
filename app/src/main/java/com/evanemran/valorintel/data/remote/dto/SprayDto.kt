package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.Spray
import com.evanemran.valorintel.domain.models.SprayLevel
import com.google.gson.annotations.SerializedName

data class SprayDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("category") val category: String? = null,
    @SerializedName("themeUuid") val themeUuid: String? = null,
    @SerializedName("isNullSpray") val isNullSpray: Boolean? = null,
    @SerializedName("hideIfNotOwned") val hideIfNotOwned: Boolean? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("fullIcon") val fullIcon: String? = null,
    @SerializedName("fullTransparentIcon") val fullTransparentIcon: String? = null,
    @SerializedName("animationPng") val animationPng: String? = null,
    @SerializedName("animationGif") val animationGif: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null,
    @SerializedName("levels") val levels: List<SprayLevelDto?>? = null
)

data class SprayLevelDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("sprayLevel") val sprayLevel: Int? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun SprayDto.toDomain(): Spray = Spray(
    uuid = uuid,
    displayName = displayName,
    category = category,
    themeUuid = themeUuid,
    isNullSpray = isNullSpray,
    hideIfNotOwned = hideIfNotOwned,
    displayIcon = displayIcon,
    fullIcon = fullIcon,
    fullTransparentIcon = fullTransparentIcon,
    animationPng = animationPng,
    animationGif = animationGif,
    assetPath = assetPath,
    levels = levels?.filterNotNull()?.map { it.toDomain() } ?: emptyList()
)

fun SprayLevelDto.toDomain(): SprayLevel = SprayLevel(
    uuid = uuid,
    sprayLevel = sprayLevel,
    displayName = displayName,
    displayIcon = displayIcon,
    assetPath = assetPath
)
