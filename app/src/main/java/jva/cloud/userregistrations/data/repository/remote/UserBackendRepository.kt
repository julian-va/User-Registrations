package jva.cloud.userregistrations.data.repository.remote

import jva.cloud.userregistrations.data.dto.UserDto
import retrofit2.Response
import retrofit2.http.GET

interface UserBackendRepository {
    @GET(value = "user")
    suspend fun getAllUser(): Response<List<UserDto>>
}