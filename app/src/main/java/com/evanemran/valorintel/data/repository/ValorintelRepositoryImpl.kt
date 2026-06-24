package com.evanemran.valorintel.data.repository

import com.evanemran.valorintel.data.remote.dto.toDomain
import com.evanemran.valorintel.data.remote.services.ValorintelApiService
import com.evanemran.valorintel.domain.models.Agent
import com.evanemran.valorintel.domain.models.Buddy
import com.evanemran.valorintel.domain.models.BuddyLevel
import com.evanemran.valorintel.domain.models.Bundle
import com.evanemran.valorintel.domain.models.Ceremony
import com.evanemran.valorintel.domain.models.CompetitiveSeason
import com.evanemran.valorintel.domain.models.CompetitiveTier
import com.evanemran.valorintel.domain.models.ContentTier
import com.evanemran.valorintel.domain.models.Contract
import com.evanemran.valorintel.domain.models.Currency
import com.evanemran.valorintel.domain.models.Event
import com.evanemran.valorintel.domain.models.GameMode
import com.evanemran.valorintel.domain.models.GameModeEquippable
import com.evanemran.valorintel.domain.models.Gear
import com.evanemran.valorintel.domain.models.LevelBorder
import com.evanemran.valorintel.domain.models.PlayerCard
import com.evanemran.valorintel.domain.models.PlayerTitle
import com.evanemran.valorintel.domain.models.Season
import com.evanemran.valorintel.domain.models.Spray
import com.evanemran.valorintel.domain.models.SprayLevel
import com.evanemran.valorintel.domain.models.Theme
import com.evanemran.valorintel.domain.models.ValorantMap
import com.evanemran.valorintel.domain.models.Version
import com.evanemran.valorintel.domain.models.Weapon
import com.evanemran.valorintel.domain.models.WeaponSkin
import com.evanemran.valorintel.domain.models.WeaponSkinChroma
import com.evanemran.valorintel.domain.models.WeaponSkinLevel
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class ValorintelRepositoryImpl(private val api: ValorintelApiService) : ValorintelRepository {

    // region Agents
    override suspend fun getAgents(isPlayableCharacter: Boolean, language: String?): List<Agent> =
        api.fetchAgents(isPlayableCharacter = isPlayableCharacter, language = language)
            .data?.filterNotNull()?.map { it.toDomain() } ?: emptyList()

    override suspend fun getAgent(uuid: String, language: String?): Agent? =
        api.fetchAgent(uuid, language).data?.toDomain()
    // endregion

    // region Buddies
    override suspend fun getBuddies(language: String?): List<Buddy> =
        api.fetchBuddies(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getBuddy(uuid: String, language: String?): Buddy? =
        api.fetchBuddy(uuid, language).data?.toDomain()

    override suspend fun getBuddyLevels(language: String?): List<BuddyLevel> =
        api.fetchBuddyLevels(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getBuddyLevel(uuid: String, language: String?): BuddyLevel? =
        api.fetchBuddyLevel(uuid, language).data?.toDomain()
    // endregion

    // region Bundles
    override suspend fun getBundles(language: String?): List<Bundle> =
        api.fetchBundles(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getBundle(uuid: String, language: String?): Bundle? =
        api.fetchBundle(uuid, language).data?.toDomain()
    // endregion

    // region Ceremonies
    override suspend fun getCeremonies(language: String?): List<Ceremony> =
        api.fetchCeremonies(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getCeremony(uuid: String, language: String?): Ceremony? =
        api.fetchCeremony(uuid, language).data?.toDomain()
    // endregion

    // region Competitive Tiers
    override suspend fun getCompetitiveTiers(language: String?): List<CompetitiveTier> =
        api.fetchCompetitiveTiers(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getCompetitiveTier(uuid: String, language: String?): CompetitiveTier? =
        api.fetchCompetitiveTier(uuid, language).data?.toDomain()
    // endregion

    // region Content Tiers
    override suspend fun getContentTiers(language: String?): List<ContentTier> =
        api.fetchContentTiers(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getContentTier(uuid: String, language: String?): ContentTier? =
        api.fetchContentTier(uuid, language).data?.toDomain()
    // endregion

    // region Contracts
    override suspend fun getContracts(language: String?): List<Contract> =
        api.fetchContracts(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getContract(uuid: String, language: String?): Contract? =
        api.fetchContract(uuid, language).data?.toDomain()
    // endregion

    // region Currencies
    override suspend fun getCurrencies(language: String?): List<Currency> =
        api.fetchCurrencies(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getCurrency(uuid: String, language: String?): Currency? =
        api.fetchCurrency(uuid, language).data?.toDomain()
    // endregion

    // region Events
    override suspend fun getEvents(language: String?): List<Event> =
        api.fetchEvents(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getEvent(uuid: String, language: String?): Event? =
        api.fetchEvent(uuid, language).data?.toDomain()
    // endregion

    // region Game Modes
    override suspend fun getGameModes(language: String?): List<GameMode> =
        api.fetchGameModes(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getGameMode(uuid: String, language: String?): GameMode? =
        api.fetchGameMode(uuid, language).data?.toDomain()

    override suspend fun getGameModeEquippables(language: String?): List<GameModeEquippable> =
        api.fetchGameModeEquippables(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getGameModeEquippable(uuid: String, language: String?): GameModeEquippable? =
        api.fetchGameModeEquippable(uuid, language).data?.toDomain()
    // endregion

    // region Gear
    override suspend fun getGear(language: String?): List<Gear> =
        api.fetchGear(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getGearItem(uuid: String, language: String?): Gear? =
        api.fetchGearItem(uuid, language).data?.toDomain()
    // endregion

    // region Level Borders
    override suspend fun getLevelBorders(language: String?): List<LevelBorder> =
        api.fetchLevelBorders(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getLevelBorder(uuid: String, language: String?): LevelBorder? =
        api.fetchLevelBorder(uuid, language).data?.toDomain()
    // endregion

    // region Maps
    override suspend fun getMaps(language: String?): List<ValorantMap> =
        api.fetchMaps(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getMap(uuid: String, language: String?): ValorantMap? =
        api.fetchMap(uuid, language).data?.toDomain()
    // endregion

    // region Player Cards
    override suspend fun getPlayerCards(language: String?): List<PlayerCard> =
        api.fetchPlayerCards(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getPlayerCard(uuid: String, language: String?): PlayerCard? =
        api.fetchPlayerCard(uuid, language).data?.toDomain()
    // endregion

    // region Player Titles
    override suspend fun getPlayerTitles(language: String?): List<PlayerTitle> =
        api.fetchPlayerTitles(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getPlayerTitle(uuid: String, language: String?): PlayerTitle? =
        api.fetchPlayerTitle(uuid, language).data?.toDomain()
    // endregion

    // region Seasons
    override suspend fun getSeasons(language: String?): List<Season> =
        api.fetchSeasons(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getSeason(uuid: String, language: String?): Season? =
        api.fetchSeason(uuid, language).data?.toDomain()

    override suspend fun getCompetitiveSeasons(language: String?): List<CompetitiveSeason> =
        api.fetchCompetitiveSeasons(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getCompetitiveSeason(uuid: String, language: String?): CompetitiveSeason? =
        api.fetchCompetitiveSeason(uuid, language).data?.toDomain()
    // endregion

    // region Sprays
    override suspend fun getSprays(language: String?): List<Spray> =
        api.fetchSprays(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getSpray(uuid: String, language: String?): Spray? =
        api.fetchSpray(uuid, language).data?.toDomain()

    override suspend fun getSprayLevels(language: String?): List<SprayLevel> =
        api.fetchSprayLevels(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getSprayLevel(uuid: String, language: String?): SprayLevel? =
        api.fetchSprayLevel(uuid, language).data?.toDomain()
    // endregion

    // region Themes
    override suspend fun getThemes(language: String?): List<Theme> =
        api.fetchThemes(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getTheme(uuid: String, language: String?): Theme? =
        api.fetchTheme(uuid, language).data?.toDomain()
    // endregion

    // region Weapons
    override suspend fun getWeapons(language: String?): List<Weapon> =
        api.fetchWeapons(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getWeapon(uuid: String, language: String?): Weapon? =
        api.fetchWeapon(uuid, language).data?.toDomain()

    override suspend fun getWeaponSkins(language: String?): List<WeaponSkin> =
        api.fetchWeaponSkins(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getWeaponSkin(uuid: String, language: String?): WeaponSkin? =
        api.fetchWeaponSkin(uuid, language).data?.toDomain()

    override suspend fun getWeaponSkinChromas(language: String?): List<WeaponSkinChroma> =
        api.fetchWeaponSkinChromas(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getWeaponSkinChroma(uuid: String, language: String?): WeaponSkinChroma? =
        api.fetchWeaponSkinChroma(uuid, language).data?.toDomain()

    override suspend fun getWeaponSkinLevels(language: String?): List<WeaponSkinLevel> =
        api.fetchWeaponSkinLevels(language).data?.map { it.toDomain() } ?: emptyList()

    override suspend fun getWeaponSkinLevel(uuid: String, language: String?): WeaponSkinLevel? =
        api.fetchWeaponSkinLevel(uuid, language).data?.toDomain()
    // endregion

    // region Version
    override suspend fun getVersion(): Version? =
        api.fetchVersion().data?.toDomain()
    // endregion
}
