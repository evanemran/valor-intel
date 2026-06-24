package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.Contract
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class ContractsUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<Contract> = repository.getContracts(language)
    suspend fun getById(uuid: String, language: String? = null): Contract? = repository.getContract(uuid, language)
}
