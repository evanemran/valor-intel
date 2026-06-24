package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.Weapon
import com.evanemran.valorintel.domain.models.WeaponSkin
import com.evanemran.valorintel.domain.models.WeaponSkinChroma
import com.evanemran.valorintel.domain.models.WeaponSkinLevel
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class WeaponsUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<Weapon> = repository.getWeapons(language)
    suspend fun getById(uuid: String, language: String? = null): Weapon? = repository.getWeapon(uuid, language)

    suspend fun getSkins(language: String? = null): List<WeaponSkin> = repository.getWeaponSkins(language)
    suspend fun getSkinById(uuid: String, language: String? = null): WeaponSkin? = repository.getWeaponSkin(uuid, language)

    suspend fun getSkinChromas(language: String? = null): List<WeaponSkinChroma> = repository.getWeaponSkinChromas(language)
    suspend fun getSkinChromaById(uuid: String, language: String? = null): WeaponSkinChroma? = repository.getWeaponSkinChroma(uuid, language)

    suspend fun getSkinLevels(language: String? = null): List<WeaponSkinLevel> = repository.getWeaponSkinLevels(language)
    suspend fun getSkinLevelById(uuid: String, language: String? = null): WeaponSkinLevel? = repository.getWeaponSkinLevel(uuid, language)
}
