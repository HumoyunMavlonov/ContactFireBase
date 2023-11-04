package uz.gita.contactfirebase.presentation.editscreen

import kotlinx.coroutines.flow.StateFlow
import uz.gita.contactfirebase.data.ContactData

interface EditContract {
    interface ViewModel {
        val uiState: StateFlow<EditContract.UiState>
        fun eventDispatchers(intent : Intent)
    }

    data class UiState(
       val isSuccess:Boolean = true
    )

    interface Intent  {
        data class EditContact(
            val contactData: ContactData
        ) : Intent
        object MoveToMainScreen : Intent
    }
}