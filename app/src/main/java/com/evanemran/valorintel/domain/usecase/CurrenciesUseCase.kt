package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.Currency
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class CurrenciesUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<Currency> = repository.getCurrencies(language)
    suspend fun getById(uuid: String, language: String? = null): Currency? = repository.getCurrency(uuid, language)
}
