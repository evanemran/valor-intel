package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.Gear
import com.evanemran.valorintel.domain.models.GearDetail
import com.google.gson.annotations.SerializedName

data class GearDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("descriptions") val descriptions: List<String?>? = null,
    @SerializedName("details") val details: List<GearDetailDto?>? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null,
    @SerializedName("shopData") val shopData: ShopDataDto? = null
)

data class GearDetailDto(
    @SerializedName("name") val name: String? = null,
    @SerializedName("value") val value: String? = null
)

fun GearDto.toDomain(): Gear = Gear(
    uuid = uuid,
    displayName = displayName,
    description = description,
    descriptions = descriptions?.filterNotNull() ?: emptyList(),
    details = details?.filterNotNull()?.map { it.toDomain() } ?: emptyList(),
    displayIcon = displayIcon,
    assetPath = assetPath,
    shopData = shopData?.toDomain()
)

fun GearDetailDto.toDomain(): GearDetail = GearDetail(
    name = name,
    value = value
)
