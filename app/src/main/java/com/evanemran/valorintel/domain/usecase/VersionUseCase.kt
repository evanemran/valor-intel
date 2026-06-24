package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.Version
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class VersionUseCase(private val repository: ValorintelRepository) {
    suspend operator fun invoke(): Version? = repository.getVersion()
}
