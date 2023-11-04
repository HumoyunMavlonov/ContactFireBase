package uz.gita.contactfirebase.presentation.main.impl

import uz.gita.contactfirebase.navigation.AppNavigator
import uz.gita.contactfirebase.presentation.addscreen.AddScreen
import uz.gita.contactfirebase.presentation.editscreen.EditScreen
import uz.gita.contactfirebase.presentation.main.MainScreenDirection
import uz.gita.contactfirebase.data.ContactData
import javax.inject.Inject

class MainScreenDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : MainScreenDirection {
    override suspend fun moveToAdd() {
        navigator.openScreenSaveStack(AddScreen())
    }
    override suspend fun moveToEdit(contactData: ContactData) {
        navigator.openScreenSaveStack(EditScreen(contactData))
    }
}