package com.evanemran.valorintel.di

import com.evanemran.valorintel.core.ApiConstants
import com.evanemran.valorintel.data.remote.services.ValorintelApiService
import com.evanemran.valorintel.data.repository.ValorintelRepositoryImpl
import com.evanemran.valorintel.domain.repository.ValorintelRepository
import com.evanemran.valorintel.domain.usecase.AgentsUseCase
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

    val agentsUseCase: AgentsUseCase by lazy {
        AgentsUseCase(valorintelRepository)
    }

}