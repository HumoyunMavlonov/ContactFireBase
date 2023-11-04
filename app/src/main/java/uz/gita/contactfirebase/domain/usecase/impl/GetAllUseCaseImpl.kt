package uz.gita.contactfirebase.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.contactfirebase.data.ContactData
import uz.gita.contactfirebase.domain.repository.AppRepository
import uz.gita.contactfirebase.domain.usecase.GetAllUseCase
import javax.inject.Inject

class GetAllUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) :GetAllUseCase{
    override fun getAllContactUseCase(): Flow<Result<List<ContactData>>> = repository.getAllContacts()

}