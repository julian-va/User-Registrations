package jva.cloud.userregistrations.presentation.navigation

object RouterOption {
    object UpdateParam {
        const val ID: String = "id"
        const val NAME: String = "name"
        const val EMAIL: String = "email"
        const val ROLE: String = "role"
        const val BASE_ROUTE = "user-update"
        const val FULL_ROUTE = "$BASE_ROUTE/{$ID}/{$NAME}/{$EMAIL}/{$ROLE}"
    }
}