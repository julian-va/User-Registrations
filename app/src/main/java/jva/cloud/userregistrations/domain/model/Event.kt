package jva.cloud.userregistrations.domain.model

import jva.cloud.userregistrations.util.DateUtilApp

data class Event<T>(
    val topicName: String,
    val date: String = DateUtilApp.parseDatePatter(),
    val type: EventType,
    val messages: T,
)