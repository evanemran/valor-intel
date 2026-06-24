package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.LevelBorder
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class LevelBordersUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<LevelBorder> = repository.getLevelBorders(language)
    suspend fun getById(uuid: String, language: String? = null): LevelBorder? = repository.getLevelBorder(uuid, language)
}
