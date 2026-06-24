package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.AdsStats
import com.evanemran.valorintel.domain.models.AirBurstStats
import com.evanemran.valorintel.domain.models.AltShotgunStats
import com.evanemran.valorintel.domain.models.DamageRange
import com.evanemran.valorintel.domain.models.Weapon
import com.evanemran.valorintel.domain.models.WeaponSkin
import com.evanemran.valorintel.domain.models.WeaponSkinChroma
import com.evanemran.valorintel.domain.models.WeaponSkinLevel
import com.evanemran.valorintel.domain.models.WeaponStats
import com.google.gson.annotations.SerializedName

data class WeaponDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("category") val category: String? = null,
    @SerializedName("defaultSkinUuid") val defaultSkinUuid: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("killStreamIcon") val killStreamIcon: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null,
    @SerializedName("weaponStats") val weaponStats: WeaponStatsDto? = null,
    @SerializedName("shopData") val shopData: ShopDataDto? = null,
    @SerializedName("skins") val skins: List<WeaponSkinDto?>? = null
)

data class WeaponStatsDto(
    @SerializedName("fireRate") val fireRate: Double? = null,
    @SerializedName("magazineSize") val magazineSize: Int? = null,
    @SerializedName("runSpeedMultiplier") val runSpeedMultiplier: Double? = null,
    @SerializedName("equipTimeSeconds") val equipTimeSeconds: Double? = null,
    @SerializedName("reloadTimeSeconds") val reloadTimeSeconds: Double? = null,
    @SerializedName("firstBulletAccuracy") val firstBulletAccuracy: Double? = null,
    @SerializedName("shotgunPelletCount") val shotgunPelletCount: Int? = null,
    @SerializedName("wallPenetration") val wallPenetration: String? = null,
    @SerializedName("feature") val feature: String? = null,
    @SerializedName("fireMode") val fireMode: String? = null,
    @SerializedName("altFireType") val altFireType: String? = null,
    @SerializedName("adsStats") val adsStats: AdsStatsDto? = null,
    @SerializedName("altShotgunStats") val altShotgunStats: AltShotgunStatsDto? = null,
    @SerializedName("airBurstStats") val airBurstStats: AirBurstStatsDto? = null,
    @SerializedName("damageRanges") val damageRanges: List<DamageRangeDto?>? = null
)

data class AdsStatsDto(
    @SerializedName("zoomMultiplier") val zoomMultiplier: Double? = null,
    @SerializedName("fireRate") val fireRate: Double? = null,
    @SerializedName("runSpeedMultiplier") val runSpeedMultiplier: Double? = null,
    @SerializedName("burstCount") val burstCount: Int? = null,
    @SerializedName("firstBulletAccuracy") val firstBulletAccuracy: Double? = null
)

data class AltShotgunStatsDto(
    @SerializedName("shotgunPelletCount") val shotgunPelletCount: Int? = null,
    @SerializedName("burstRate") val burstRate: Double? = null
)

data class AirBurstStatsDto(
    @SerializedName("shotgunPelletCount") val shotgunPelletCount: Int? = null,
    @SerializedName("burstDistance") val burstDistance: Double? = null
)

data class DamageRangeDto(
    @SerializedName("rangeStartMeters") val rangeStartMeters: Double? = null,
    @SerializedName("rangeEndMeters") val rangeEndMeters: Double? = null,
    @SerializedName("headDamage") val headDamage: Double? = null,
    @SerializedName("bodyDamage") val bodyDamage: Double? = null,
    @SerializedName("legDamage") val legDamage: Double? = null
)

data class WeaponSkinDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("themeUuid") val themeUuid: String? = null,
    @SerializedName("contentTierUuid") val contentTierUuid: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("wallpaper") val wallpaper: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null,
    @SerializedName("chromas") val chromas: List<WeaponSkinChromaDto?>? = null,
    @SerializedName("levels") val levels: List<WeaponSkinLevelDto?>? = null
)

data class WeaponSkinChromaDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("fullRender") val fullRender: String? = null,
    @SerializedName("swatch") val swatch: String? = null,
    @SerializedName("streamedVideo") val streamedVideo: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

data class WeaponSkinLevelDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("levelItem") val levelItem: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("streamedVideo") val streamedVideo: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

fun WeaponDto.toDomain(): Weapon = Weapon(
    uuid = uuid,
    displayName = displayName,
    category = category,
    defaultSkinUuid = defaultSkinUuid,
    displayIcon = displayIcon,
    killStreamIcon = killStreamIcon,
    assetPath = assetPath,
    weaponStats = weaponStats?.toDomain(),
    shopData = shopData?.toDomain(),
    skins = skins?.filterNotNull()?.map { it.toDomain() } ?: emptyList()
)

fun WeaponStatsDto.toDomain(): WeaponStats = WeaponStats(
    fireRate = fireRate,
    magazineSize = magazineSize,
    runSpeedMultiplier = runSpeedMultiplier,
    equipTimeSeconds = equipTimeSeconds,
    reloadTimeSeconds = reloadTimeSeconds,
    firstBulletAccuracy = firstBulletAccuracy,
    shotgunPelletCount = shotgunPelletCount,
    wallPenetration = wallPenetration,
    feature = feature,
    fireMode = fireMode,
    altFireType = altFireType,
    adsStats = adsStats?.toDomain(),
    altShotgunStats = altShotgunStats?.toDomain(),
    airBurstStats = airBurstStats?.toDomain(),
    damageRanges = damageRanges?.filterNotNull()?.map { it.toDomain() } ?: emptyList()
)

fun AdsStatsDto.toDomain(): AdsStats = AdsStats(
    zoomMultiplier = zoomMultiplier,
    fireRate = fireRate,
    runSpeedMultiplier = runSpeedMultiplier,
    burstCount = burstCount,
    firstBulletAccuracy = firstBulletAccuracy
)

fun AltShotgunStatsDto.toDomain(): AltShotgunStats = AltShotgunStats(
    shotgunPelletCount = shotgunPelletCount,
    burstRate = burstRate
)

fun AirBurstStatsDto.toDomain(): AirBurstStats = AirBurstStats(
    shotgunPelletCount = shotgunPelletCount,
    burstDistance = burstDistance
)

fun DamageRangeDto.toDomain(): DamageRange = DamageRange(
    rangeStartMeters = rangeStartMeters,
    rangeEndMeters = rangeEndMeters,
    headDamage = headDamage,
    bodyDamage = bodyDamage,
    legDamage = legDamage
)

fun WeaponSkinDto.toDomain(): WeaponSkin = WeaponSkin(
    uuid = uuid,
    displayName = displayName,
    themeUuid = themeUuid,
    contentTierUuid = contentTierUuid,
    displayIcon = displayIcon,
    wallpaper = wallpaper,
    assetPath = assetPath,
    chromas = chromas?.filterNotNull()?.map { it.toDomain() } ?: emptyList(),
    levels = levels?.filterNotNull()?.map { it.toDomain() } ?: emptyList()
)

fun WeaponSkinChromaDto.toDomain(): WeaponSkinChroma = WeaponSkinChroma(
    uuid = uuid,
    displayName = displayName,
    displayIcon = displayIcon,
    fullRender = fullRender,
    swatch = swatch,
    streamedVideo = streamedVideo,
    assetPath = assetPath
)

fun WeaponSkinLevelDto.toDomain(): WeaponSkinLevel = WeaponSkinLevel(
    uuid = uuid,
    displayName = displayName,
    levelItem = levelItem,
    displayIcon = displayIcon,
    streamedVideo = streamedVideo,
    assetPath = assetPath
)
