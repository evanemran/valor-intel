package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.GameMode
import com.evanemran.valorintel.domain.models.GameModeEquippable
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class GameModesUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<GameMode> = repository.getGameModes(language)
    suspend fun getById(uuid: String, language: String? = null): GameMode? = repository.getGameMode(uuid, language)
    suspend fun getEquippables(language: String? = null): List<GameModeEquippable> = repository.getGameModeEquippables(language)
    suspend fun getEquippableById(uuid: String, language: String? = null): GameModeEquippable? = repository.getGameModeEquippable(uuid, language)
}
