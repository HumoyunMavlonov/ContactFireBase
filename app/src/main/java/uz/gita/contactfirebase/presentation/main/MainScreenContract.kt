package uz.gita.contactfirebase.presentation.main

import kotlinx.coroutines.flow.StateFlow
import uz.gita.contactfirebase.data.ContactData

sealed interface MainScreenContract {

    interface ViewModel {
        val uiState : StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
        fun loadData()
    }

    interface Intent {
        object ClickAdd : Intent
        object load:Intent

        data class ClickDelete(
            val data: ContactData
        ) : Intent

        data class ClickEdit(
            val data: ContactData
        ) : Intent
    }

    data class UiState(
        val allContacts : List<ContactData> = emptyList()
    )

}