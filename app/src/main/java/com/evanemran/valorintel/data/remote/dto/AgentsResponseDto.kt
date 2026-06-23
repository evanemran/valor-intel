package com.evanemran.valorintel.data.remote.dto
import com.evanemran.valorintel.domain.models.Ability
import com.evanemran.valorintel.domain.models.Agent
import com.evanemran.valorintel.domain.models.RecruitmentData
import com.evanemran.valorintel.domain.models.Role
import com.google.gson.annotations.SerializedName
import kotlin.collections.emptyList


data class AgentsResponseDto(
    @SerializedName("data") val `data`: List<AgentDto?>? = null,
    @SerializedName("status") val status: Int? = null
)

data class AgentDto(
    @SerializedName("abilities") val abilities: List<AbilityDto?>? = emptyList(),
    @SerializedName("assetPath") val assetPath: String? = null,
    @SerializedName("background") val background: String? = null,
    @SerializedName("backgroundGradientColors") val backgroundGradientColors: List<String?>? = emptyList(),
    @SerializedName("bustPortrait") val bustPortrait: String? = null,
    @SerializedName("characterTags") val characterTags: List<String?>? = emptyList(),
    @SerializedName("description") val description: String? = null,
    @SerializedName("developerName") val developerName: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("displayIconSmall") val displayIconSmall: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("fullPortrait") val fullPortrait: String? = null,
    @SerializedName("fullPortraitV2") val fullPortraitV2: String? = null,
    @SerializedName("homeScreenPromoTileImage") val homeScreenPromoTileImage: String? = null,
    @SerializedName("isAvailableForTest") val isAvailableForTest: Boolean? = null,
    @SerializedName("isBaseContent") val isBaseContent: Boolean? = null,
    @SerializedName("isFullPortraitRightFacing") val isFullPortraitRightFacing: Boolean? = null,
    @SerializedName("isPlayableCharacter") val isPlayableCharacter: Boolean? = null,
    @SerializedName("killfeedPortrait") val killfeedPortrait: String? = null,
    @SerializedName("minimapPortrait") val minimapPortrait: String? = null,
    @SerializedName("recruitmentData") val recruitmentData: RecruitmentDataDto? = null,
    @SerializedName("releaseDate") val releaseDate: String? = null,
    @SerializedName("role") val role: RoleDto? = null,
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("voiceLine") val voiceLine: String? = null
)

data class AbilityDto(
    @SerializedName("description") val description: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("slot") val slot: String? = null
)

data class RecruitmentDataDto(
    @SerializedName("counterId") val counterId: String? = null,
    @SerializedName("endDate") val endDate: String? = null,
    @SerializedName("levelVpCostOverride") val levelVpCostOverride: Int? = null,
    @SerializedName("milestoneId") val milestoneId: String? = null,
    @SerializedName("milestoneThreshold") val milestoneThreshold: Int? = null,
    @SerializedName("startDate") val startDate: String? = null,
    @SerializedName("useLevelVpCostOverride") val useLevelVpCostOverride: Boolean? = null
)

data class RoleDto(
    @SerializedName("assetPath") val assetPath: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("uuid") val uuid: String? = null
)


fun AgentDto.toDomain(): Agent = Agent(
    abilities = abilities
        ?.filterNotNull()
        ?.map { it.toDomain() }
        ?: emptyList(),
    assetPath = assetPath,
    background = background,
    backgroundGradientColors = backgroundGradientColors,
    bustPortrait = bustPortrait,
    characterTags = characterTags,
    description = description,
    developerName = developerName,
    displayIcon = displayIcon,
    displayIconSmall = displayIconSmall,
    displayName = displayName,
    fullPortrait = fullPortrait,
    fullPortraitV2 = fullPortraitV2,
    homeScreenPromoTileImage = homeScreenPromoTileImage,
    isAvailableForTest = isAvailableForTest,
    isBaseContent = isBaseContent,
    isFullPortraitRightFacing = isFullPortraitRightFacing,
    isPlayableCharacter = isPlayableCharacter,
    killfeedPortrait = killfeedPortrait,
    minimapPortrait = minimapPortrait,
    recruitmentData = recruitmentData?.toDomain(),
    releaseDate = releaseDate,
    role = role?.toDomain(),
    uuid = uuid,
    voiceLine = voiceLine
)

fun AbilityDto.toDomain(): Ability = Ability(
    description = description,
    displayIcon = displayIcon,
    displayName = displayName,
    slot = slot
)

fun RecruitmentDataDto.toDomain(): RecruitmentData = RecruitmentData(
    counterId = counterId,
    endDate = endDate,
    levelVpCostOverride = levelVpCostOverride,
    milestoneId = milestoneId,
    milestoneThreshold = milestoneThreshold,
    startDate = startDate,
    useLevelVpCostOverride = useLevelVpCostOverride
)

fun RoleDto.toDomain(): Role = Role(
    assetPath = assetPath,
    description = description,
    displayIcon = displayIcon,
    displayName = displayName,
    uuid = uuid
)



