package uz.gita.contactfirebase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.contactfirebase.navigation.AppNavigationDispatcher
import uz.gita.contactfirebase.navigation.AppNavigationHandler
import uz.gita.contactfirebase.navigation.AppNavigator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @Singleton
    fun bindAppNavigation(impl : AppNavigationDispatcher) : AppNavigator

    @Binds
    @Singleton
    fun bindAppNavigationHandler(impl : AppNavigationDispatcher) : AppNavigationHandler

}