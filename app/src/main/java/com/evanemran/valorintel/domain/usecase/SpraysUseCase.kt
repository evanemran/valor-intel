package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.Spray
import com.evanemran.valorintel.domain.models.SprayLevel
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class SpraysUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<Spray> = repository.getSprays(language)
    suspend fun getById(uuid: String, language: String? = null): Spray? = repository.getSpray(uuid, language)
    suspend fun getLevels(language: String? = null): List<SprayLevel> = repository.getSprayLevels(language)
    suspend fun getLevelById(uuid: String, language: String? = null): SprayLevel? = repository.getSprayLevel(uuid, language)
}
