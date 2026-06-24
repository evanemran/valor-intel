package com.evanemran.valorintel.di

import com.evanemran.valorintel.core.ApiConstants
import com.evanemran.valorintel.data.remote.services.ValorintelApiService
import com.evanemran.valorintel.data.repository.ValorintelRepositoryImpl
import com.evanemran.valorintel.domain.repository.ValorintelRepository
import com.evanemran.valorintel.domain.usecase.AgentsUseCase
import com.evanemran.valorintel.domain.usecase.BuddiesUseCase
import com.evanemran.valorintel.domain.usecase.BundlesUseCase
import com.evanemran.valorintel.domain.usecase.CeremoniesUseCase
import com.evanemran.valorintel.domain.usecase.CompetitiveTiersUseCase
import com.evanemran.valorintel.domain.usecase.ContentTiersUseCase
import com.evanemran.valorintel.domain.usecase.ContractsUseCase
import com.evanemran.valorintel.domain.usecase.CurrenciesUseCase
import com.evanemran.valorintel.domain.usecase.EventsUseCase
import com.evanemran.valorintel.domain.usecase.GameModesUseCase
import com.evanemran.valorintel.domain.usecase.GearUseCase
import com.evanemran.valorintel.domain.usecase.LevelBordersUseCase
import com.evanemran.valorintel.domain.usecase.MapsUseCase
import com.evanemran.valorintel.domain.usecase.PlayerCardsUseCase
import com.evanemran.valorintel.domain.usecase.PlayerTitlesUseCase
import com.evanemran.valorintel.domain.usecase.SeasonsUseCase
import com.evanemran.valorintel.domain.usecase.SpraysUseCase
import com.evanemran.valorintel.domain.usecase.ThemesUseCase
import com.evanemran.valorintel.domain.usecase.VersionUseCase
import com.evanemran.valorintel.domain.usecase.WeaponsUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {

    private val okHttpClient: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val valorintelApiService: ValorintelApiService by lazy {
        retrofit.create(ValorintelApiService::class.java)
    }

    private val valorintelRepository: ValorintelRepository by lazy {
        ValorintelRepositoryImpl(api = valorintelApiService)
    }

    val agentsUseCase: AgentsUseCase by lazy { AgentsUseCase(valorintelRepository) }
    val buddiesUseCase: BuddiesUseCase by lazy { BuddiesUseCase(valorintelRepository) }
    val bundlesUseCase: BundlesUseCase by lazy { BundlesUseCase(valorintelRepository) }
    val ceremoniesUseCase: CeremoniesUseCase by lazy { CeremoniesUseCase(valorintelRepository) }
    val competitiveTiersUseCase: CompetitiveTiersUseCase by lazy { CompetitiveTiersUseCase(valorintelRepository) }
    val contentTiersUseCase: ContentTiersUseCase by lazy { ContentTiersUseCase(valorintelRepository) }
    val contractsUseCase: ContractsUseCase by lazy { ContractsUseCase(valorintelRepository) }
    val currenciesUseCase: CurrenciesUseCase by lazy { CurrenciesUseCase(valorintelRepository) }
    val eventsUseCase: EventsUseCase by lazy { EventsUseCase(valorintelRepository) }
    val gameModesUseCase: GameModesUseCase by lazy { GameModesUseCase(valorintelRepository) }
    val gearUseCase: GearUseCase by lazy { GearUseCase(valorintelRepository) }
    val levelBordersUseCase: LevelBordersUseCase by lazy { LevelBordersUseCase(valorintelRepository) }
    val mapsUseCase: MapsUseCase by lazy { MapsUseCase(valorintelRepository) }
    val playerCardsUseCase: PlayerCardsUseCase by lazy { PlayerCardsUseCase(valorintelRepository) }
    val playerTitlesUseCase: PlayerTitlesUseCase by lazy { PlayerTitlesUseCase(valorintelRepository) }
    val seasonsUseCase: SeasonsUseCase by lazy { SeasonsUseCase(valorintelRepository) }
    val spraysUseCase: SpraysUseCase by lazy { SpraysUseCase(valorintelRepository) }
    val themesUseCase: ThemesUseCase by lazy { ThemesUseCase(valorintelRepository) }
    val weaponsUseCase: WeaponsUseCase by lazy { WeaponsUseCase(valorintelRepository) }
    val versionUseCase: VersionUseCase by lazy { VersionUseCase(valorintelRepository) }
}
