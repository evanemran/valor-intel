package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.Bundle
import com.google.gson.annotations.SerializedName

data class BundleDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("displayNameSubText") val displayNameSubText: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("extraDescription") val extraDescription: String? = null,
    @SerializedName("promoDescription") val promoDescription: String? = null,
    @SerializedName("useAdditionalContext") val useAdditionalContext: Boolean? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("displayIcon2") val displayIcon2: String? = null,
    @SerializedName("displayIcon3") val displayIcon3: String? = null,
    @SerializedName("logoIcon") val logoIcon: String? = null,
    @SerializedName("verticalPromoImage") val verticalPromoImage: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun BundleDto.toDomain(): Bundle = Bundle(
    uuid = uuid,
    displayName = displayName,
    displayNameSubText = displayNameSubText,
    description = description,
    extraDescription = extraDescription,
    promoDescription = promoDescription,
    useAdditionalContext = useAdditionalContext,
    displayIcon = displayIcon,
    displayIcon2 = displayIcon2,
    displayIcon3 = displayIcon3,
    logoIcon = logoIcon,
    verticalPromoImage = verticalPromoImage,
    assetPath = assetPath
)
