package com.evanemran.valorintel.domain.models

data class Contract(
    val uuid: String? = null,
    val displayName: String? = null,
    val displayIcon: String? = null,
    val shipIt: Boolean? = null,
    val useLevelVPCostOverride: Boolean? = null,
    val levelVPCostOverride: Int? = null,
    val freeRewardScheduleUuid: String? = null,
    val content: ContractContent? = null,
    val assetPath: String? = null
)

data class ContractContent(
    val relationType: String? = null,
    val relationUuid: String? = null,
    val chapters: List<ContractChapter> = emptyList(),
    val premiumRewardScheduleUuid: String? = null,
    val premiumVPCost: Int? = null
)

data class ContractChapter(
    val isEpilogue: Boolean? = null,
    val levels: List<ChapterLevel> = emptyList(),
    val freeRewards: List<ChapterReward> = emptyList()
)

data class ChapterLevel(
    val reward: ChapterReward? = null,
    val xp: Int? = null,
    val vpCost: Int? = null,
    val isPurchasableWithVP: Boolean? = null,
    val doughCost: Int? = null,
    val isPurchasableWithDough: Boolean? = null
)

data class ChapterReward(
    val type: String? = null,
    val uuid: String? = null,
    val amount: Int? = null,
    val isHighlighted: Boolean? = null
)
