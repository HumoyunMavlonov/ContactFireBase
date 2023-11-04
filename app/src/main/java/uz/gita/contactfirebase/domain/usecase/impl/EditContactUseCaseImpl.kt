package uz.gita.contactfirebase.domain.usecase.impl

import android.net.Uri
import uz.gita.contactfirebase.domain.repository.AppRepository
import uz.gita.contactfirebase.domain.usecase.EditContactUseCase
import javax.inject.Inject

class EditContactUseCaseImpl @Inject constructor(
    val repository: AppRepository
):EditContactUseCase {
    override fun editContactUseCase(id: String, name: String, phone: String,image: Uri) {
        repository.editContact(id,name, phone,image)
    }
}