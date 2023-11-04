package uz.gita.contactfirebase.presentation.editscreen.impl

import uz.gita.contactfirebase.navigation.AppNavigator
import uz.gita.contactfirebase.presentation.editscreen.EditDirection
import javax.inject.Inject

class EditDirectionImpl @Inject constructor(
    val appNavigator: AppNavigator
) :EditDirection{
    override suspend fun back() {
        appNavigator.back()
    }
}