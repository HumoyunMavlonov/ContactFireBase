package uz.gita.contactfirebase.domain.usecase

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import uz.gita.contactfirebase.data.ContactData

interface AddContactUseCase {

    fun addContactsUseCase(name:String,phone:String,image: Uri):Flow<Boolean>
}