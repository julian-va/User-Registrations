package jva.cloud.userregistrations.domain.usecase.user.impl

import jva.cloud.userregistrations.data.repository.remote.UserRepository
import jva.cloud.userregistrations.domain.model.Event
import jva.cloud.userregistrations.domain.usecase.user.UserEventService
import javax.inject.Inject

class UserEventServiceImpl @Inject constructor(private val userRepository: UserRepository) :
    UserEventService {

    override suspend fun sendEvent(event: Event<Any>) {
        try {
            val response = userRepository.sendEventProducer(event)
            println("event sent correctly: ${response.isSuccessful}")
        } catch (ex: Exception) {
            println("failed to send the event to the server, the error is: ${ex.message}")
        }
    }
}