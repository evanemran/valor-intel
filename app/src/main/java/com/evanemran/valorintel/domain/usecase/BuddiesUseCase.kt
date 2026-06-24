package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.Buddy
import com.evanemran.valorintel.domain.models.BuddyLevel
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class BuddiesUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<Buddy> = repository.getBuddies(language)
    suspend fun getById(uuid: String, language: String? = null): Buddy? = repository.getBuddy(uuid, language)
    suspend fun getLevels(language: String? = null): List<BuddyLevel> = repository.getBuddyLevels(language)
    suspend fun getLevelById(uuid: String, language: String? = null): BuddyLevel? = repository.getBuddyLevel(uuid, language)
}
