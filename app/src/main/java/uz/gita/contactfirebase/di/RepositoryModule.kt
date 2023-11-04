package uz.gita.contactfirebase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.contactfirebase.data.impl.AppRepositoryImpl
import uz.gita.contactfirebase.domain.repository.AppRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAppUseCase(impl : AppRepositoryImpl) : AppRepository

}