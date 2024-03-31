package jva.cloud.userregistrations.domain.usecase.user.impl

import jva.cloud.userregistrations.data.dto.UserDto
import jva.cloud.userregistrations.data.repository.remote.UserBackendRepository
import jva.cloud.userregistrations.domain.usecase.user.GetUsersService
import javax.inject.Inject

class GetUsersServiceImpl @Inject constructor(private val userBackendRepository: UserBackendRepository) :
    GetUsersService {
    override suspend fun getAllUser(): List<UserDto> {
        var users: List<UserDto> = listOf()
        try {
            val response = userBackendRepository.getAllUser()
            if (response.isSuccessful) {
                response.body()?.let { userDto -> users = userDto }
            }
            println("users recovered correctly: ${response.isSuccessful}")
        } catch (ex: Exception) {
            println("failed to users recovered to the server, the error is: ${ex.message}")
        }
        return users
    }
}