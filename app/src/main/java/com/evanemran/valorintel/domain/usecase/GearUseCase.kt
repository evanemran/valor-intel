package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.Gear
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class GearUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<Gear> = repository.getGear(language)
    suspend fun getById(uuid: String, language: String? = null): Gear? = repository.getGearItem(uuid, language)
}
