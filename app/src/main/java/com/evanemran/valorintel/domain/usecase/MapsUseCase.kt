package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.ValorantMap
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class MapsUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<ValorantMap> = repository.getMaps(language)
    suspend fun getById(uuid: String, language: String? = null): ValorantMap? = repository.getMap(uuid, language)
}
