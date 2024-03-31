package jva.cloud.userregistrations.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jva.cloud.userregistrations.data.repository.remote.UserBackendRepository
import jva.cloud.userregistrations.data.repository.remote.UserRepository
import jva.cloud.userregistrations.util.ConstantApp
import jva.cloud.userregistrations.util.RetrofitQualifier
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private val interceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides()
    @Singleton
    @RetrofitQualifier.RetrofitProducer
    fun providesRetrofitProducer(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ConstantApp.BASE_URL_PRODUCER_KAFKA)
            .client(client)
            .build()
    }

    @Provides()
    @Singleton
    @RetrofitQualifier.RetrofitBackEnd
    fun providesRetrofitBackend(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ConstantApp.BASE_URL_BACKEND)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideUserRepository(@RetrofitQualifier.RetrofitProducer retrofit: Retrofit): UserRepository {
        return retrofit.create(UserRepository::class.java)
    }

    @Singleton
    @Provides
    fun provideUserBackendRepository(@RetrofitQualifier.RetrofitBackEnd retrofit: Retrofit): UserBackendRepository {
        return retrofit.create(UserBackendRepository::class.java)
    }
}