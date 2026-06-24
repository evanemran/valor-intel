package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.PlayerTitle
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class PlayerTitlesUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<PlayerTitle> = repository.getPlayerTitles(language)
    suspend fun getById(uuid: String, language: String? = null): PlayerTitle? = repository.getPlayerTitle(uuid, language)
}
