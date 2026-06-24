package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.CompetitiveSeason
import com.evanemran.valorintel.domain.models.Season
import com.evanemran.valorintel.domain.models.SeasonBorder
import com.google.gson.annotations.SerializedName

data class SeasonDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("startTime") val startTime: String? = null,
    @SerializedName("endTime") val endTime: String? = null,
    @SerializedName("parentUuid") val parentUuid: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

data class CompetitiveSeasonDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("startTime") val startTime: String? = null,
    @SerializedName("endTime") val endTime: String? = null,
    @SerializedName("seasonUuid") val seasonUuid: String? = null,
    @SerializedName("competitiveTiersUuid") val competitiveTiersUuid: String? = null,
    @SerializedName("borders") val borders: List<SeasonBorderDto?>? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

data class SeasonBorderDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("level") val level: Int? = null,
    @SerializedName("winsRequired") val winsRequired: Int? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("smallIcon") val smallIcon: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun SeasonDto.toDomain(): Season = Season(
    uuid = uuid,
    displayName = displayName,
    title = title,
    type = type,
    startTime = startTime,
    endTime = endTime,
    parentUuid = parentUuid,
    assetPath = assetPath
)

fun CompetitiveSeasonDto.toDomain(): CompetitiveSeason = CompetitiveSeason(
    uuid = uuid,
    startTime = startTime,
    endTime = endTime,
    seasonUuid = seasonUuid,
    competitiveTiersUuid = competitiveTiersUuid,
    borders = borders?.filterNotNull()?.map { it.toDomain() } ?: emptyList(),
    assetPath = assetPath
)

fun SeasonBorderDto.toDomain(): SeasonBorder = SeasonBorder(
    uuid = uuid,
    level = level,
    winsRequired = winsRequired,
    displayIcon = displayIcon,
    smallIcon = smallIcon,
    assetPath = assetPath
)
