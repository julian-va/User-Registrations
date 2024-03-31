package jva.cloud.userregistrations.data.dto

data class UserUpdateDto(
    val id: Long,
    val name: String,
    val email: String,
    val role: String,
    val password: String?,
)
