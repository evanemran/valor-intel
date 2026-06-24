package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.Theme
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class ThemesUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<Theme> = repository.getThemes(language)
    suspend fun getById(uuid: String, language: String? = null): Theme? = repository.getTheme(uuid, language)
}
