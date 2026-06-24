package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.ChapterLevel
import com.evanemran.valorintel.domain.models.ChapterReward
import com.evanemran.valorintel.domain.models.Contract
import com.evanemran.valorintel.domain.models.ContractChapter
import com.evanemran.valorintel.domain.models.ContractContent
import com.google.gson.annotations.SerializedName

data class ContractDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("shipIt") val shipIt: Boolean? = null,
    @SerializedName("useLevelVPCostOverride") val useLevelVPCostOverride: Boolean? = null,
    @SerializedName("levelVPCostOverride") val levelVPCostOverride: Int? = null,
    @SerializedName("freeRewardScheduleUuid") val freeRewardScheduleUuid: String? = null,
    @SerializedName("content") val content: ContractContentDto? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

data class ContractContentDto(
    @SerializedName("relationType") val relationType: String? = null,
    @SerializedName("relationUuid") val relationUuid: String? = null,
    @SerializedName("chapters") val chapters: List<ContractChapterDto?>? = null,
    @SerializedName("premiumRewardScheduleUuid") val premiumRewardScheduleUuid: String? = null,
    @SerializedName("premiumVPCost") val premiumVPCost: Int? = null
)

data class ContractChapterDto(
    @SerializedName("isEpilogue") val isEpilogue: Boolean? = null,
    @SerializedName("levels") val levels: List<ChapterLevelDto?>? = null,
    @SerializedName("freeRewards") val freeRewards: List<ChapterRewardDto?>? = null
)

data class ChapterLevelDto(
    @SerializedName("reward") val reward: ChapterRewardDto? = null,
    @SerializedName("xp") val xp: Int? = null,
    @SerializedName("vpCost") val vpCost: Int? = null,
    @SerializedName("isPurchasableWithVP") val isPurchasableWithVP: Boolean? = null,
    @SerializedName("doughCost") val doughCost: Int? = null,
    @SerializedName("isPurchasableWithDough") val isPurchasableWithDough: Boolean? = null
)

data class ChapterRewardDto(
    @SerializedName("type") val type: String? = null,
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("amount") val amount: Int? = null,
    @SerializedName("isHighlighted") val isHighlighted: Boolean? = null
)

fun ContractDto.toDomain(): Contract = Contract(
    uuid = uuid,
    displayName = displayName,
    displayIcon = displayIcon,
    shipIt = shipIt,
    useLevelVPCostOverride = useLevelVPCostOverride,
    levelVPCostOverride = levelVPCostOverride,
    freeRewardScheduleUuid = freeRewardScheduleUuid,
    content = content?.toDomain(),
    assetPath = assetPath
)

fun ContractContentDto.toDomain(): ContractContent = ContractContent(
    relationType = relationType,
    relationUuid = relationUuid,
    chapters = chapters?.filterNotNull()?.map { it.toDomain() } ?: emptyList(),
    premiumRewardScheduleUuid = premiumRewardScheduleUuid,
    premiumVPCost = premiumVPCost
)

fun ContractChapterDto.toDomain(): ContractChapter = ContractChapter(
    isEpilogue = isEpilogue,
    levels = levels?.filterNotNull()?.map { it.toDomain() } ?: emptyList(),
    freeRewards = freeRewards?.filterNotNull()?.map { it.toDomain() } ?: emptyList()
)

fun ChapterLevelDto.toDomain(): ChapterLevel = ChapterLevel(
    reward = reward?.toDomain(),
    xp = xp,
    vpCost = vpCost,
    isPurchasableWithVP = isPurchasableWithVP,
    doughCost = doughCost,
    isPurchasableWithDough = isPurchasableWithDough
)

fun ChapterRewardDto.toDomain(): ChapterReward = ChapterReward(
    type = type,
    uuid = uuid,
    amount = amount,
    isHighlighted = isHighlighted
)
