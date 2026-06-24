package com.evanemran.valorintel.domain.models

data class Weapon(
    val uuid: String? = null,
    val displayName: String? = null,
    val category: String? = null,
    val defaultSkinUuid: String? = null,
    val displayIcon: String? = null,
    val killStreamIcon: String? = null,
    val assetPath: String? = null,
    val weaponStats: WeaponStats? = null,
    val shopData: ShopData? = null,
    val skins: List<WeaponSkin> = emptyList()
)

data class WeaponStats(
    val fireRate: Double? = null,
    val magazineSize: Int? = null,
    val runSpeedMultiplier: Double? = null,
    val equipTimeSeconds: Double? = null,
    val reloadTimeSeconds: Double? = null,
    val firstBulletAccuracy: Double? = null,
    val shotgunPelletCount: Int? = null,
    val wallPenetration: String? = null,
    val feature: String? = null,
    val fireMode: String? = null,
    val altFireType: String? = null,
    val adsStats: AdsStats? = null,
    val altShotgunStats: AltShotgunStats? = null,
    val airBurstStats: AirBurstStats? = null,
    val damageRanges: List<DamageRange> = emptyList()
)

data class AdsStats(
    val zoomMultiplier: Double? = null,
    val fireRate: Double? = null,
    val runSpeedMultiplier: Double? = null,
    val burstCount: Int? = null,
    val firstBulletAccuracy: Double? = null
)

data class AltShotgunStats(
    val shotgunPelletCount: Int? = null,
    val burstRate: Double? = null
)

data class AirBurstStats(
    val shotgunPelletCount: Int? = null,
    val burstDistance: Double? = null
)

data class DamageRange(
    val rangeStartMeters: Double? = null,
    val rangeEndMeters: Double? = null,
    val headDamage: Double? = null,
    val bodyDamage: Double? = null,
    val legDamage: Double? = null
)

data class WeaponSkin(
    val uuid: String? = null,
    val displayName: String? = null,
    val themeUuid: String? = null,
    val contentTierUuid: String? = null,
    val displayIcon: String? = null,
    val wallpaper: String? = null,
    val assetPath: String? = null,
    val chromas: List<WeaponSkinChroma> = emptyList(),
    val levels: List<WeaponSkinLevel> = emptyList()
)

data class WeaponSkinChroma(
    val uuid: String? = null,
    val displayName: String? = null,
    val displayIcon: String? = null,
    val fullRender: String? = null,
    val swatch: String? = null,
    val streamedVideo: String? = null,
    val assetPath: String? = null
)

data class WeaponSkinLevel(
    val uuid: String? = null,
    val displayName: String? = null,
    val levelItem: String? = null,
    val displayIcon: String? = null,
    val streamedVideo: String? = null,
    val assetPath: String? = null
)
