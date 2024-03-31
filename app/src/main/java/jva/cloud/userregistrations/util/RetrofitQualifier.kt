package jva.cloud.userregistrations.util

import javax.inject.Qualifier

object RetrofitQualifier {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RetrofitProducer

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RetrofitBackEnd
}