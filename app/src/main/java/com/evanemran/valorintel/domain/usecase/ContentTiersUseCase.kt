package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.ContentTier
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class ContentTiersUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<ContentTier> = repository.getContentTiers(language)
    suspend fun getById(uuid: String, language: String? = null): ContentTier? = repository.getContentTier(uuid, language)
}
