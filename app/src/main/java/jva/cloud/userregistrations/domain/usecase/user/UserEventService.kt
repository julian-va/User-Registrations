package jva.cloud.userregistrations.domain.usecase.user

import jva.cloud.userregistrations.domain.model.Event

interface UserEventService {
    suspend fun sendEvent(event: Event<Any>): Unit
}