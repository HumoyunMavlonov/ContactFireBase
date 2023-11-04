package uz.gita.contactfirebase.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.contactfirebase.data.ContactData

interface GetAllUseCase {

    fun getAllContactUseCase():Flow<Result<List<ContactData>>>
}