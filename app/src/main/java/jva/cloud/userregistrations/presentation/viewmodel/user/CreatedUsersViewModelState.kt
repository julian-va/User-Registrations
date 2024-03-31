package jva.cloud.userregistrations.presentation.viewmodel.user

import jva.cloud.userregistrations.data.dto.UserDto
import jva.cloud.userregistrations.util.ConstantApp

data class CreatedUsersViewModelState(
    val users: List<UserDto> = listOf(),
    val progressIndicator: Boolean = ConstantApp.FALSE
)
