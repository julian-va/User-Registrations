package jva.cloud.userregistrations.domain.model

data class User(
    val name: String,
    val email: String,
    val password: String,
    val role: String,
)
