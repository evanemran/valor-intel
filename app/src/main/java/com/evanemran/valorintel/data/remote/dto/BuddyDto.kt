package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.Buddy
import com.evanemran.valorintel.domain.models.BuddyLevel
import com.google.gson.annotations.SerializedName

data class BuddyDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("isHiddenIfNotOwned") val isHiddenIfNotOwned: Boolean? = null,
    @SerializedName("themeUuid") val themeUuid: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null,
    @SerializedName("levels") val levels: List<BuddyLevelDto?>? = null
)

data class BuddyLevelDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("charmLevel") val charmLevel: Int? = null,
    @SerializedName("hideIfNotOwned") val hideIfNotOwned: Boolean? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun BuddyDto.toDomain(): Buddy = Buddy(
    uuid = uuid,
    displayName = displayName,
    isHiddenIfNotOwned = isHiddenIfNotOwned,
    themeUuid = themeUuid,
    displayIcon = displayIcon,
    assetPath = assetPath,
    levels = levels?.filterNotNull()?.map { it.toDomain() } ?: emptyList()
)

fun BuddyLevelDto.toDomain(): BuddyLevel = BuddyLevel(
    uuid = uuid,
    charmLevel = charmLevel,
    hideIfNotOwned = hideIfNotOwned,
    displayName = displayName,
    displayIcon = displayIcon,
    assetPath = assetPath
)
