package com.evanemran.valorintel.data.remote.services

import com.evanemran.valorintel.core.ApiConstants
import com.evanemran.valorintel.data.remote.dto.AgentDto
import com.evanemran.valorintel.data.remote.dto.AgentsResponseDto
import com.evanemran.valorintel.data.remote.dto.ApiResponseDto
import com.evanemran.valorintel.data.remote.dto.BuddyDto
import com.evanemran.valorintel.data.remote.dto.BuddyLevelDto
import com.evanemran.valorintel.data.remote.dto.BundleDto
import com.evanemran.valorintel.data.remote.dto.CeremonyDto
import com.evanemran.valorintel.data.remote.dto.CompetitiveSeasonDto
import com.evanemran.valorintel.data.remote.dto.CompetitiveTierDto
import com.evanemran.valorintel.data.remote.dto.ContentTierDto
import com.evanemran.valorintel.data.remote.dto.ContractDto
import com.evanemran.valorintel.data.remote.dto.CurrencyDto
import com.evanemran.valorintel.data.remote.dto.EventDto
import com.evanemran.valorintel.data.remote.dto.GameModeDto
import com.evanemran.valorintel.data.remote.dto.GameModeEquippableDto
import com.evanemran.valorintel.data.remote.dto.GearDto
import com.evanemran.valorintel.data.remote.dto.LevelBorderDto
import com.evanemran.valorintel.data.remote.dto.MapDto
import com.evanemran.valorintel.data.remote.dto.PlayerCardDto
import com.evanemran.valorintel.data.remote.dto.PlayerTitleDto
import com.evanemran.valorintel.data.remote.dto.SeasonDto
import com.evanemran.valorintel.data.remote.dto.SprayDto
import com.evanemran.valorintel.data.remote.dto.SprayLevelDto
import com.evanemran.valorintel.data.remote.dto.ThemeDto
import com.evanemran.valorintel.data.remote.dto.VersionDto
import com.evanemran.valorintel.data.remote.dto.WeaponDto
import com.evanemran.valorintel.data.remote.dto.WeaponSkinChromaDto
import com.evanemran.valorintel.data.remote.dto.WeaponSkinDto
import com.evanemran.valorintel.data.remote.dto.WeaponSkinLevelDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ValorintelApiService {

    // region Agents
    @GET(ApiConstants.AGENTS)
    suspend fun fetchAgents(
        @Query("isPlayableCharacter") isPlayableCharacter: Boolean,
        @Query("language") language: String? = null,
    ): AgentsResponseDto

    @GET("${ApiConstants.AGENTS}/{uuid}")
    suspend fun fetchAgent(
        @Path("uuid") uuid: String,
        @Query("language") language: String? = null,
    ): ApiResponseDto<AgentDto>
    // endregion

    // region Buddies
    @GET(ApiConstants.BUDDIES)
    suspend fun fetchBuddies(@Query("language") language: String? = null): ApiResponseDto<List<BuddyDto>>

    @GET("${ApiConstants.BUDDIES}/{uuid}")
    suspend fun fetchBuddy(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<BuddyDto>

    @GET(ApiConstants.BUDDY_LEVELS)
    suspend fun fetchBuddyLevels(@Query("language") language: String? = null): ApiResponseDto<List<BuddyLevelDto>>

    @GET("${ApiConstants.BUDDY_LEVELS}/{uuid}")
    suspend fun fetchBuddyLevel(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<BuddyLevelDto>
    // endregion

    // region Bundles
    @GET(ApiConstants.BUNDLES)
    suspend fun fetchBundles(@Query("language") language: String? = null): ApiResponseDto<List<BundleDto>>

    @GET("${ApiConstants.BUNDLES}/{uuid}")
    suspend fun fetchBundle(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<BundleDto>
    // endregion

    // region Ceremonies
    @GET(ApiConstants.CEREMONIES)
    suspend fun fetchCeremonies(@Query("language") language: String? = null): ApiResponseDto<List<CeremonyDto>>

    @GET("${ApiConstants.CEREMONIES}/{uuid}")
    suspend fun fetchCeremony(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<CeremonyDto>
    // endregion

    // region Competitive Tiers
    @GET(ApiConstants.COMPETITIVE_TIERS)
    suspend fun fetchCompetitiveTiers(@Query("language") language: String? = null): ApiResponseDto<List<CompetitiveTierDto>>

    @GET("${ApiConstants.COMPETITIVE_TIERS}/{uuid}")
    suspend fun fetchCompetitiveTier(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<CompetitiveTierDto>
    // endregion

    // region Content Tiers
    @GET(ApiConstants.CONTENT_TIERS)
    suspend fun fetchContentTiers(@Query("language") language: String? = null): ApiResponseDto<List<ContentTierDto>>

    @GET("${ApiConstants.CONTENT_TIERS}/{uuid}")
    suspend fun fetchContentTier(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<ContentTierDto>
    // endregion

    // region Contracts
    @GET(ApiConstants.CONTRACTS)
    suspend fun fetchContracts(@Query("language") language: String? = null): ApiResponseDto<List<ContractDto>>

    @GET("${ApiConstants.CONTRACTS}/{uuid}")
    suspend fun fetchContract(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<ContractDto>
    // endregion

    // region Currencies
    @GET(ApiConstants.CURRENCIES)
    suspend fun fetchCurrencies(@Query("language") language: String? = null): ApiResponseDto<List<CurrencyDto>>

    @GET("${ApiConstants.CURRENCIES}/{uuid}")
    suspend fun fetchCurrency(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<CurrencyDto>
    // endregion

    // region Events
    @GET(ApiConstants.EVENTS)
    suspend fun fetchEvents(@Query("language") language: String? = null): ApiResponseDto<List<EventDto>>

    @GET("${ApiConstants.EVENTS}/{uuid}")
    suspend fun fetchEvent(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<EventDto>
    // endregion

    // region Game Modes
    @GET(ApiConstants.GAME_MODES)
    suspend fun fetchGameModes(@Query("language") language: String? = null): ApiResponseDto<List<GameModeDto>>

    @GET("${ApiConstants.GAME_MODES}/{uuid}")
    suspend fun fetchGameMode(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<GameModeDto>

    @GET(ApiConstants.GAME_MODE_EQUIPPABLES)
    suspend fun fetchGameModeEquippables(@Query("language") language: String? = null): ApiResponseDto<List<GameModeEquippableDto>>

    @GET("${ApiConstants.GAME_MODE_EQUIPPABLES}/{uuid}")
    suspend fun fetchGameModeEquippable(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<GameModeEquippableDto>
    // endregion

    // region Gear
    @GET(ApiConstants.GEAR)
    suspend fun fetchGear(@Query("language") language: String? = null): ApiResponseDto<List<GearDto>>

    @GET("${ApiConstants.GEAR}/{uuid}")
    suspend fun fetchGearItem(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<GearDto>
    // endregion

    // region Level Borders
    @GET(ApiConstants.LEVEL_BORDERS)
    suspend fun fetchLevelBorders(@Query("language") language: String? = null): ApiResponseDto<List<LevelBorderDto>>

    @GET("${ApiConstants.LEVEL_BORDERS}/{uuid}")
    suspend fun fetchLevelBorder(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<LevelBorderDto>
    // endregion

    // region Maps
    @GET(ApiConstants.MAPS)
    suspend fun fetchMaps(@Query("language") language: String? = null): ApiResponseDto<List<MapDto>>

    @GET("${ApiConstants.MAPS}/{uuid}")
    suspend fun fetchMap(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<MapDto>
    // endregion

    // region Player Cards
    @GET(ApiConstants.PLAYER_CARDS)
    suspend fun fetchPlayerCards(@Query("language") language: String? = null): ApiResponseDto<List<PlayerCardDto>>

    @GET("${ApiConstants.PLAYER_CARDS}/{uuid}")
    suspend fun fetchPlayerCard(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<PlayerCardDto>
    // endregion

    // region Player Titles
    @GET(ApiConstants.PLAYER_TITLES)
    suspend fun fetchPlayerTitles(@Query("language") language: String? = null): ApiResponseDto<List<PlayerTitleDto>>

    @GET("${ApiConstants.PLAYER_TITLES}/{uuid}")
    suspend fun fetchPlayerTitle(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<PlayerTitleDto>
    // endregion

    // region Seasons
    @GET(ApiConstants.SEASONS)
    suspend fun fetchSeasons(@Query("language") language: String? = null): ApiResponseDto<List<SeasonDto>>

    @GET("${ApiConstants.SEASONS}/{uuid}")
    suspend fun fetchSeason(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<SeasonDto>

    @GET(ApiConstants.COMPETITIVE_SEASONS)
    suspend fun fetchCompetitiveSeasons(@Query("language") language: String? = null): ApiResponseDto<List<CompetitiveSeasonDto>>

    @GET("${ApiConstants.COMPETITIVE_SEASONS}/{uuid}")
    suspend fun fetchCompetitiveSeason(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<CompetitiveSeasonDto>
    // endregion

    // region Sprays
    @GET(ApiConstants.SPRAYS)
    suspend fun fetchSprays(@Query("language") language: String? = null): ApiResponseDto<List<SprayDto>>

    @GET("${ApiConstants.SPRAYS}/{uuid}")
    suspend fun fetchSpray(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<SprayDto>

    @GET(ApiConstants.SPRAY_LEVELS)
    suspend fun fetchSprayLevels(@Query("language") language: String? = null): ApiResponseDto<List<SprayLevelDto>>

    @GET("${ApiConstants.SPRAY_LEVELS}/{uuid}")
    suspend fun fetchSprayLevel(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<SprayLevelDto>
    // endregion

    // region Themes
    @GET(ApiConstants.THEMES)
    suspend fun fetchThemes(@Query("language") language: String? = null): ApiResponseDto<List<ThemeDto>>

    @GET("${ApiConstants.THEMES}/{uuid}")
    suspend fun fetchTheme(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<ThemeDto>
    // endregion

    // region Weapons
    @GET(ApiConstants.WEAPONS)
    suspend fun fetchWeapons(@Query("language") language: String? = null): ApiResponseDto<List<WeaponDto>>

    @GET("${ApiConstants.WEAPONS}/{uuid}")
    suspend fun fetchWeapon(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<WeaponDto>

    @GET(ApiConstants.WEAPON_SKINS)
    suspend fun fetchWeaponSkins(@Query("language") language: String? = null): ApiResponseDto<List<WeaponSkinDto>>

    @GET("${ApiConstants.WEAPON_SKINS}/{uuid}")
    suspend fun fetchWeaponSkin(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<WeaponSkinDto>

    @GET(ApiConstants.WEAPON_SKIN_CHROMAS)
    suspend fun fetchWeaponSkinChromas(@Query("language") language: String? = null): ApiResponseDto<List<WeaponSkinChromaDto>>

    @GET("${ApiConstants.WEAPON_SKIN_CHROMAS}/{uuid}")
    suspend fun fetchWeaponSkinChroma(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<WeaponSkinChromaDto>

    @GET(ApiConstants.WEAPON_SKIN_LEVELS)
    suspend fun fetchWeaponSkinLevels(@Query("language") language: String? = null): ApiResponseDto<List<WeaponSkinLevelDto>>

    @GET("${ApiConstants.WEAPON_SKIN_LEVELS}/{uuid}")
    suspend fun fetchWeaponSkinLevel(@Path("uuid") uuid: String, @Query("language") language: String? = null): ApiResponseDto<WeaponSkinLevelDto>
    // endregion

    // region Version
    @GET(ApiConstants.VERSION)
    suspend fun fetchVersion(): ApiResponseDto<VersionDto>
    // endregion
}
