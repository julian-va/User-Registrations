package jva.cloud.userregistrations.util

import java.text.SimpleDateFormat
import java.util.Date

object DateUtilApp {

    fun parseDatePatter(): String {
        val dateDraft = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        return formatter.format(dateDraft)
    }
}