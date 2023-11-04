package uz.gita.contactfirebase.domain.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import uz.gita.contactfirebase.data.ContactData

interface AppRepository {

    fun getAllContacts(): Flow<Result<List<ContactData>>>

    fun addContacts(name:String,phone:String,image: Uri):Flow<Boolean>

    //    fun uploadImage(uri: Uri, name: String): Flow<Result<UploadData>>
    fun editContact(id:String,name:String,phone:String, image:Uri):Flow<Boolean>

    fun deleteContact(id:String)

}