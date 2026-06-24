package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.Theme
import com.google.gson.annotations.SerializedName

data class ThemeDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("storeFeaturedImage") val storeFeaturedImage: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun ThemeDto.toDomain(): Theme = Theme(
    uuid = uuid,
    displayName = displayName,
    displayIcon = displayIcon,
    storeFeaturedImage = storeFeaturedImage,
    assetPath = assetPath
)
