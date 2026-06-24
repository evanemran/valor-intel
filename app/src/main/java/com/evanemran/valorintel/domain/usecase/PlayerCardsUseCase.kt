package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.PlayerCard
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class PlayerCardsUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<PlayerCard> = repository.getPlayerCards(language)
    suspend fun getById(uuid: String, language: String? = null): PlayerCard? = repository.getPlayerCard(uuid, language)
}
