package com.evanemran.valorintel.domain.repository

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

interface ValorintelRepository {

    // Agents
    suspend fun getAgents(isPlayableCharacter: Boolean, language: String? = null): List<Agent>
    suspend fun getAgent(uuid: String, language: String? = null): Agent?

    // Buddies
    suspend fun getBuddies(language: String? = null): List<Buddy>
    suspend fun getBuddy(uuid: String, language: String? = null): Buddy?
    suspend fun getBuddyLevels(language: String? = null): List<BuddyLevel>
    suspend fun getBuddyLevel(uuid: String, language: String? = null): BuddyLevel?

    // Bundles
    suspend fun getBundles(language: String? = null): List<Bundle>
    suspend fun getBundle(uuid: String, language: String? = null): Bundle?

    // Ceremonies
    suspend fun getCeremonies(language: String? = null): List<Ceremony>
    suspend fun getCeremony(uuid: String, language: String? = null): Ceremony?

    // Competitive Tiers
    suspend fun getCompetitiveTiers(language: String? = null): List<CompetitiveTier>
    suspend fun getCompetitiveTier(uuid: String, language: String? = null): CompetitiveTier?

    // Content Tiers
    suspend fun getContentTiers(language: String? = null): List<ContentTier>
    suspend fun getContentTier(uuid: String, language: String? = null): ContentTier?

    // Contracts
    suspend fun getContracts(language: String? = null): List<Contract>
    suspend fun getContract(uuid: String, language: String? = null): Contract?

    // Currencies
    suspend fun getCurrencies(language: String? = null): List<Currency>
    suspend fun getCurrency(uuid: String, language: String? = null): Currency?

    // Events
    suspend fun getEvents(language: String? = null): List<Event>
    suspend fun getEvent(uuid: String, language: String? = null): Event?

    // Game Modes
    suspend fun getGameModes(language: String? = null): List<GameMode>
    suspend fun getGameMode(uuid: String, language: String? = null): GameMode?
    suspend fun getGameModeEquippables(language: String? = null): List<GameModeEquippable>
    suspend fun getGameModeEquippable(uuid: String, language: String? = null): GameModeEquippable?

    // Gear
    suspend fun getGear(language: String? = null): List<Gear>
    suspend fun getGearItem(uuid: String, language: String? = null): Gear?

    // Level Borders
    suspend fun getLevelBorders(language: String? = null): List<LevelBorder>
    suspend fun getLevelBorder(uuid: String, language: String? = null): LevelBorder?

    // Maps
    suspend fun getMaps(language: String? = null): List<ValorantMap>
    suspend fun getMap(uuid: String, language: String? = null): ValorantMap?

    // Player Cards
    suspend fun getPlayerCards(language: String? = null): List<PlayerCard>
    suspend fun getPlayerCard(uuid: String, language: String? = null): PlayerCard?

    // Player Titles
    suspend fun getPlayerTitles(language: String? = null): List<PlayerTitle>
    suspend fun getPlayerTitle(uuid: String, language: String? = null): PlayerTitle?

    // Seasons
    suspend fun getSeasons(language: String? = null): List<Season>
    suspend fun getSeason(uuid: String, language: String? = null): Season?
    suspend fun getCompetitiveSeasons(language: String? = null): List<CompetitiveSeason>
    suspend fun getCompetitiveSeason(uuid: String, language: String? = null): CompetitiveSeason?

    // Sprays
    suspend fun getSprays(language: String? = null): List<Spray>
    suspend fun getSpray(uuid: String, language: String? = null): Spray?
    suspend fun getSprayLevels(language: String? = null): List<SprayLevel>
    suspend fun getSprayLevel(uuid: String, language: String? = null): SprayLevel?

    // Themes
    suspend fun getThemes(language: String? = null): List<Theme>
    suspend fun getTheme(uuid: String, language: String? = null): Theme?

    // Weapons
    suspend fun getWeapons(language: String? = null): List<Weapon>
    suspend fun getWeapon(uuid: String, language: String? = null): Weapon?
    suspend fun getWeaponSkins(language: String? = null): List<WeaponSkin>
    suspend fun getWeaponSkin(uuid: String, language: String? = null): WeaponSkin?
    suspend fun getWeaponSkinChromas(language: String? = null): List<WeaponSkinChroma>
    suspend fun getWeaponSkinChroma(uuid: String, language: String? = null): WeaponSkinChroma?
    suspend fun getWeaponSkinLevels(language: String? = null): List<WeaponSkinLevel>
    suspend fun getWeaponSkinLevel(uuid: String, language: String? = null): WeaponSkinLevel?

    // Version
    suspend fun getVersion(): Version?
}
