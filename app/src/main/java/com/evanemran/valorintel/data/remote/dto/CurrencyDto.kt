package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.Currency
import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("displayNameSingular") val displayNameSingular: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("largeIcon") val largeIcon: String? = null,
    @SerializedName("rewardPreviewIcon") val rewardPreviewIcon: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun CurrencyDto.toDomain(): Currency = Currency(
    uuid = uuid,
    displayName = displayName,
    displayNameSingular = displayNameSingular,
    displayIcon = displayIcon,
    largeIcon = largeIcon,
    rewardPreviewIcon = rewardPreviewIcon,
    assetPath = assetPath
)
