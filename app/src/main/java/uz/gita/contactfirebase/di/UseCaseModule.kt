package uz.gita.contactfirebase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.contactfirebase.domain.usecase.AddContactUseCase
import uz.gita.contactfirebase.domain.usecase.DeleteContact
import uz.gita.contactfirebase.domain.usecase.EditContactUseCase
import uz.gita.contactfirebase.domain.usecase.GetAllUseCase
import uz.gita.contactfirebase.domain.usecase.impl.AddContactUseCaseImpl
import uz.gita.contactfirebase.domain.usecase.impl.DeleteContactUseCaseImpl
import uz.gita.contactfirebase.domain.usecase.impl.EditContactUseCaseImpl
import uz.gita.contactfirebase.domain.usecase.impl.GetAllUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    @Singleton
    fun bindAppUseCase(impl : GetAllUseCaseImpl) : GetAllUseCase

    @Binds
    @Singleton
    fun bindAddContactUseCase(impl:AddContactUseCaseImpl):AddContactUseCase

    @Binds
    @Singleton
    fun bindEditContactUseCase(impl:EditContactUseCaseImpl):EditContactUseCase

    @Binds
    @Singleton
    fun bindDeleteContactUseCase(impl:DeleteContactUseCaseImpl):DeleteContact

}