package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.Bundle
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class BundlesUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<Bundle> = repository.getBundles(language)
    suspend fun getById(uuid: String, language: String? = null): Bundle? = repository.getBundle(uuid, language)
}
