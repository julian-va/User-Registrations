package jva.cloud.userregistrations.domain.usecase.user

import jva.cloud.userregistrations.data.dto.UserDto

interface GetUsersService {
    suspend fun getAllUser(): List<UserDto>
}