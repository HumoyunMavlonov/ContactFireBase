package uz.gita.contactfirebase.domain.usecase

import android.net.Uri

interface EditContactUseCase {

    fun editContactUseCase(id:String,name:String,phone:String,image: Uri)
}