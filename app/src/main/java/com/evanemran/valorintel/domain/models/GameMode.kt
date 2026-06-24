package com.evanemran.valorintel.domain.models

data class GameMode(
    val uuid: String? = null,
    val displayName: String? = null,
    val description: String? = null,
    val duration: String? = null,
    val economyType: String? = null,
    val allowsMatchTimeouts: Boolean? = null,
    val allowsCustomGameReplays: Boolean? = null,
    val isTeamVoiceAllowed: Boolean? = null,
    val isMinimapHidden: Boolean? = null,
    val orbCount: Int? = null,
    val roundsPerHalf: Int? = null,
    val teamRoles: List<String> = emptyList(),
    val gameFeatureOverrides: List<GameFeatureOverride> = emptyList(),
    val gameRuleBoolOverrides: List<GameRuleBoolOverride> = emptyList(),
    val displayIcon: String? = null,
    val listViewIconTall: String? = null,
    val assetPath: String? = null
)

data class GameFeatureOverride(
    val featureName: String? = null,
    val state: Boolean? = null
)

data class GameRuleBoolOverride(
    val ruleName: String? = null,
    val state: Boolean? = null
)

data class GameModeEquippable(
    val uuid: String? = null,
    val displayName: String? = null,
    val category: String? = null,
    val displayIcon: String? = null,
    val killStreamIcon: String? = null,
    val assetPath: String? = null
)
