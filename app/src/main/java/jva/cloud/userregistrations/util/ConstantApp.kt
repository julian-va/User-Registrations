package jva.cloud.userregistrations.util

object ConstantApp {
    ///URL
    const val BASE_URL_PRODUCER_KAFKA: String = "http://URL:8081/api/orchestrator/"
    const val BASE_URL_BACKEND: String = "http://URL:3000/api/"

    //TOPIC KAFKA NAME
    const val TOPIC_USER: String = "order-topic"

    //CONSTANT
    const val EMPTY: String = ""
    const val ONE: Int = 1
    const val ID_VALUE_DEFAULT: Long = 1
    const val FALSE: Boolean = false
    const val TRUE: Boolean = true
}