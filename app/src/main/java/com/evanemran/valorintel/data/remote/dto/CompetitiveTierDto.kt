package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.CompetitiveTier
import com.evanemran.valorintel.domain.models.Tier
import com.google.gson.annotations.SerializedName

data class CompetitiveTierDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("assetObjectName") val assetObjectName: String? = null,
    @SerializedName("tiers") val tiers: List<TierDto?>? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

data class TierDto(
    @SerializedName("tier") val tier: Int? = null,
    @SerializedName("tierName") val tierName: String? = null,
    @SerializedName("division") val division: String? = null,
    @SerializedName("divisionName") val divisionName: String? = null,
    @SerializedName("color") val color: String? = null,
    @SerializedName("backgroundColor") val backgroundColor: String? = null,
    @SerializedName("smallIcon") val smallIcon: String? = null,
    @SerializedName("largeIcon") val largeIcon: String? = null,
    @SerializedName("rankTriangleDownIcon") val rankTriangleDownIcon: String? = null,
    @SerializedName("rankTriangleUpIcon") val rankTriangleUpIcon: String? = null
)

fun CompetitiveTierDto.toDomain(): CompetitiveTier = CompetitiveTier(
    uuid = uuid,
    assetObjectName = assetObjectName,
    tiers = tiers?.filterNotNull()?.map { it.toDomain() } ?: emptyList(),
    assetPath = assetPath
)

fun TierDto.toDomain(): Tier = Tier(
    tier = tier,
    tierName = tierName,
    division = division,
    divisionName = divisionName,
    color = color,
    backgroundColor = backgroundColor,
    smallIcon = smallIcon,
    largeIcon = largeIcon,
    rankTriangleDownIcon = rankTriangleDownIcon,
    rankTriangleUpIcon = rankTriangleUpIcon
)
