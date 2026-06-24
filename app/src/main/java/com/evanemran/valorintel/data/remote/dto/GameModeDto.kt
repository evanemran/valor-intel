package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.GameFeatureOverride
import com.evanemran.valorintel.domain.models.GameMode
import com.evanemran.valorintel.domain.models.GameModeEquippable
import com.evanemran.valorintel.domain.models.GameRuleBoolOverride
import com.google.gson.annotations.SerializedName

data class GameModeDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("duration") val duration: String? = null,
    @SerializedName("economyType") val economyType: String? = null,
    @SerializedName("allowsMatchTimeouts") val allowsMatchTimeouts: Boolean? = null,
    @SerializedName("allowsCustomGameReplays") val allowsCustomGameReplays: Boolean? = null,
    @SerializedName("isTeamVoiceAllowed") val isTeamVoiceAllowed: Boolean? = null,
    @SerializedName("isMinimapHidden") val isMinimapHidden: Boolean? = null,
    @SerializedName("orbCount") val orbCount: Int? = null,
    @SerializedName("roundsPerHalf") val roundsPerHalf: Int? = null,
    @SerializedName("teamRoles") val teamRoles: List<String?>? = null,
    @SerializedName("gameFeatureOverrides") val gameFeatureOverrides: List<GameFeatureOverrideDto?>? = null,
    @SerializedName("gameRuleBoolOverrides") val gameRuleBoolOverrides: List<GameRuleBoolOverrideDto?>? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("listViewIconTall") val listViewIconTall: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

data class GameFeatureOverrideDto(
    @SerializedName("featureName") val featureName: String? = null,
    @SerializedName("state") val state: Boolean? = null
)

data class GameRuleBoolOverrideDto(
    @SerializedName("ruleName") val ruleName: String? = null,
    @SerializedName("state") val state: Boolean? = null
)

data class GameModeEquippableDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("category") val category: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("killStreamIcon") val killStreamIcon: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun GameModeDto.toDomain(): GameMode = GameMode(
    uuid = uuid,
    displayName = displayName,
    description = description,
    duration = duration,
    economyType = economyType,
    allowsMatchTimeouts = allowsMatchTimeouts,
    allowsCustomGameReplays = allowsCustomGameReplays,
    isTeamVoiceAllowed = isTeamVoiceAllowed,
    isMinimapHidden = isMinimapHidden,
    orbCount = orbCount,
    roundsPerHalf = roundsPerHalf,
    teamRoles = teamRoles?.filterNotNull() ?: emptyList(),
    gameFeatureOverrides = gameFeatureOverrides?.filterNotNull()?.map { it.toDomain() } ?: emptyList(),
    gameRuleBoolOverrides = gameRuleBoolOverrides?.filterNotNull()?.map { it.toDomain() } ?: emptyList(),
    displayIcon = displayIcon,
    listViewIconTall = listViewIconTall,
    assetPath = assetPath
)

fun GameFeatureOverrideDto.toDomain(): GameFeatureOverride = GameFeatureOverride(
    featureName = featureName,
    state = state
)

fun GameRuleBoolOverrideDto.toDomain(): GameRuleBoolOverride = GameRuleBoolOverride(
    ruleName = ruleName,
    state = state
)

fun GameModeEquippableDto.toDomain(): GameModeEquippable = GameModeEquippable(
    uuid = uuid,
    displayName = displayName,
    category = category,
    displayIcon = displayIcon,
    killStreamIcon = killStreamIcon,
    assetPath = assetPath
)
