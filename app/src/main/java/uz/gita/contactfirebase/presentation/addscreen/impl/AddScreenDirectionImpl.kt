package uz.gita.contactfirebase.presentation.addscreen.impl

import uz.gita.contactfirebase.navigation.AppNavigator
import uz.gita.contactfirebase.presentation.addscreen.AddScreenDirection
import javax.inject.Inject

class AddScreenDirectionImpl @Inject constructor(
    val appNavigator: AppNavigator
) :AddScreenDirection{
    override suspend fun back() {
appNavigator.back()
    }
}