package uz.gita.contactfirebase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.contactfirebase.presentation.addscreen.AddScreenDirection
import uz.gita.contactfirebase.presentation.addscreen.impl.AddScreenDirectionImpl
import uz.gita.contactfirebase.presentation.editscreen.EditDirection
import uz.gita.contactfirebase.presentation.editscreen.impl.EditDirectionImpl
import uz.gita.contactfirebase.presentation.main.MainScreenDirection
import uz.gita.contactfirebase.presentation.main.impl.MainScreenDirectionImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    fun bindMainDirection(impl:MainScreenDirectionImpl):MainScreenDirection

    @Binds
    fun bindAddDirection(impl:AddScreenDirectionImpl):AddScreenDirection

    @Binds
    fun bindEditDirection(impl:EditDirectionImpl):EditDirection
}