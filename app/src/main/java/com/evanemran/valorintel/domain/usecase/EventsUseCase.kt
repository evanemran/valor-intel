package com.evanemran.valorintel.domain.usecase

import com.evanemran.valorintel.domain.models.Event
import com.evanemran.valorintel.domain.repository.ValorintelRepository

class EventsUseCase(private val repository: ValorintelRepository) {
    suspend fun getAll(language: String? = null): List<Event> = repository.getEvents(language)
    suspend fun getById(uuid: String, language: String? = null): Event? = repository.getEvent(uuid, language)
}
