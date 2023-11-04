package uz.gita.contactfirebase.domain.usecase.impl

import uz.gita.contactfirebase.domain.repository.AppRepository
import uz.gita.contactfirebase.domain.usecase.DeleteContact
import javax.inject.Inject

class DeleteContactUseCaseImpl @Inject constructor(
    val repository: AppRepository
) : DeleteContact{
    override fun deleteContactUseCase(id: String) {
        repository.deleteContact(id)
    }
}