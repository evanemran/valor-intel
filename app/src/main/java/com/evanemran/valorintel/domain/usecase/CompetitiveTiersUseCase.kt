package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.CompetitiveTier
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class CompetitiveTiersUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<CompetitiveTier> = repository.getCompetitiveTiers(language)
    suspend fun getById(uuid: String, language: String? = null): CompetitiveTier? = repository.getCompetitiveTier(uuid, language)
}
