package uz.gita.contactfirebase.domain.usecase.impl

import android.net.Uri
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import uz.gita.contactfirebase.domain.repository.AppRepository
import uz.gita.contactfirebase.domain.usecase.AddContactUseCase
import javax.inject.Inject

class AddContactUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) :AddContactUseCase{
    override fun addContactsUseCase(name: String, phone: String,image: Uri): Flow<Boolean> = callbackFlow {
        repository.addContacts(name, phone,image)

    }}