package uz.gita.contactfirebase.presentation.main

import uz.gita.contactfirebase.data.ContactData

interface MainScreenDirection {

    suspend fun moveToAdd()
    suspend fun moveToEdit(contactData: ContactData)

}