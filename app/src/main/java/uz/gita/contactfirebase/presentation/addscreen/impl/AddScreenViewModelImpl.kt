package uz.gita.contactfirebase.presentation.addscreen.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.contactfirebase.domain.repository.AppRepository
import uz.gita.contactfirebase.domain.usecase.AddContactUseCase
import uz.gita.contactfirebase.presentation.addscreen.AddScreenContract
import uz.gita.contactfirebase.presentation.addscreen.AddScreenDirection
import javax.inject.Inject

@HiltViewModel
class AddScreenViewModelImpl @Inject constructor(
    private val direction: AddScreenDirection,
    val appRepository: AppRepository,
    val useCase: AddContactUseCase
): ViewModel(), AddScreenContract.ViewModel {
//    override val uiState= MutableStateFlow (AddScreenContract.UiState())


    override fun onEventDispatcher(intent: AddScreenContract.Intent) {
        when(intent){
            AddScreenContract.Intent.MoveToMainScreen -> {
                viewModelScope.launch {
                    direction.back()
                }
            }
            is AddScreenContract.Intent.AddContact -> {
                viewModelScope.launch {
                    uiState.update { it.copy(isSuccess  = true) }
                 appRepository.addContacts(intent.name,intent.phone,intent.image).onEach {
                        uiState.update { it.copy(isSuccess = false) }
                        if (it) {
                            direction.back()
                        }
                    }.collect()
            }}


        }
    }

    override val uiState =MutableStateFlow(AddScreenContract.UiState())
}