package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.PlayerCard
import com.google.gson.annotations.SerializedName

data class PlayerCardDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("isHiddenIfNotOwned") val isHiddenIfNotOwned: Boolean? = null,
    @SerializedName("themeUuid") val themeUuid: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("smallArt") val smallArt: String? = null,
    @SerializedName("wideArt") val wideArt: String? = null,
    @SerializedName("largeArt") val largeArt: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun PlayerCardDto.toDomain(): PlayerCard = PlayerCard(
    uuid = uuid,
    displayName = displayName,
    isHiddenIfNotOwned = isHiddenIfNotOwned,
    themeUuid = themeUuid,
    displayIcon = displayIcon,
    smallArt = smallArt,
    wideArt = wideArt,
    largeArt = largeArt,
    assetPath = assetPath
)
