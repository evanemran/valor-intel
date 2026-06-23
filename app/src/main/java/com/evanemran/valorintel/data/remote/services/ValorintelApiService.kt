package com.evanemran.valorintel.data.remote.services

import com.evanemran.valorintel.core.ApiConstants
import com.evanemran.valorintel.data.remote.dto.AgentsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ValorintelApiService {

    @GET(ApiConstants.AGENTS)
    suspend fun fetchAgents(
        @Query("isPlayableCharacter") isPlayableCharacter: Boolean,
    ): AgentsResponseDto
}