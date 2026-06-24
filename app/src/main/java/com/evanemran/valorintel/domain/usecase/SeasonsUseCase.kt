package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.CompetitiveSeason
import com.evanemran.valorintel.domain.models.Season
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class SeasonsUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<Season> = repository.getSeasons(language)
    suspend fun getById(uuid: String, language: String? = null): Season? = repository.getSeason(uuid, language)
    suspend fun getCompetitive(language: String? = null): List<CompetitiveSeason> = repository.getCompetitiveSeasons(language)
    suspend fun getCompetitiveById(uuid: String, language: String? = null): CompetitiveSeason? = repository.getCompetitiveSeason(uuid, language)
}
