package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.PlayerTitle
import com.google.gson.annotations.SerializedName

data class PlayerTitleDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("titleText") val titleText: String? = null,
    @SerializedName("isHiddenIfNotOwned") val isHiddenIfNotOwned: Boolean? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun PlayerTitleDto.toDomain(): PlayerTitle = PlayerTitle(
    uuid = uuid,
    displayName = displayName,
    titleText = titleText,
    isHiddenIfNotOwned = isHiddenIfNotOwned,
    assetPath = assetPath
)
