package jva.cloud.userregistrations.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jva.cloud.userregistrations.domain.usecase.user.GetUsersService
import jva.cloud.userregistrations.domain.usecase.user.UserEventService
import jva.cloud.userregistrations.domain.usecase.user.impl.GetUsersServiceImpl
import jva.cloud.userregistrations.domain.usecase.user.impl.UserEventServiceImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideUserEventService(impl: UserEventServiceImpl): UserEventService

    @Binds
    abstract fun provideGetUsersService(impl: GetUsersServiceImpl): GetUsersService
}