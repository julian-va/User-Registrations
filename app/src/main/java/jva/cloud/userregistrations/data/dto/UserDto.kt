package jva.cloud.userregistrations.data.dto

import java.util.Date

data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
    val role: String,
    val password: String?,
    val creationDate: Date,
    val updateDate: Date
)
