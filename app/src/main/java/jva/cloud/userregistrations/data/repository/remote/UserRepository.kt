package jva.cloud.userregistrations.data.repository.remote

import jva.cloud.userregistrations.domain.model.Event
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRepository {
    @POST(value = "producer")
    suspend fun sendEventProducer(@Body user: Event<Any>): Response<Unit>
}