package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.Ceremony
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class CeremoniesUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<Ceremony> = repository.getCeremonies(language)
    suspend fun getById(uuid: String, language: String? = null): Ceremony? = repository.getCeremony(uuid, language)
}
