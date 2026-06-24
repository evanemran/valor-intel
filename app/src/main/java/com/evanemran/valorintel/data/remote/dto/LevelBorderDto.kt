package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.LevelBorder
import com.google.gson.annotations.SerializedName

data class LevelBorderDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("startingLevel") val startingLevel: Int? = null,
    @SerializedName("levelNumber") val levelNumber: Int? = null,
    @SerializedName("levelNumberAppearance") val levelNumberAppearance: String? = null,
    @SerializedName("smallPlayerCardAppearance") val smallPlayerCardAppearance: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun LevelBorderDto.toDomain(): LevelBorder = LevelBorder(
    uuid = uuid,
    displayName = displayName,
    startingLevel = startingLevel,
    levelNumber = levelNumber,
    levelNumberAppearance = levelNumberAppearance,
    smallPlayerCardAppearance = smallPlayerCardAppearance,
    assetPath = assetPath
)
