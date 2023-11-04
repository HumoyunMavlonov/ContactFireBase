package uz.gita.contactfirebase.presentation.addscreen

import android.net.Uri
import kotlinx.coroutines.flow.StateFlow

sealed interface AddScreenContract {

    interface ViewModel{
        fun onEventDispatcher(intent: Intent)
        val uiState:StateFlow<UiState>
    }

    data class UiState(
        val  isSuccess:Boolean=false
    )
    interface Intent {
        data class AddContact(val name: String, val phone: String, val image:Uri) : Intent
        object MoveToMainScreen: Intent
    }
}