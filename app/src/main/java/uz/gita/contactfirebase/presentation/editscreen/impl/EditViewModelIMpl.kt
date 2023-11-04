package uz.gita.contactfirebase.presentation.editscreen.impl

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import uz.gita.contactfirebase.domain.repository.AppRepository
import uz.gita.contactfirebase.domain.usecase.EditContactUseCase
import uz.gita.contactfirebase.presentation.editscreen.EditContract
import uz.gita.contactfirebase.presentation.editscreen.EditDirection
import javax.inject.Inject

@HiltViewModel
class EditViewModelIMpl @Inject constructor(
    private val direction: EditDirection,
    val useCase: EditContactUseCase,
    val appRepository: AppRepository
) : ViewModel(), EditContract.ViewModel {


    override val uiState = MutableStateFlow(EditContract.UiState())


    override fun eventDispatchers(intent: EditContract.Intent) {
        when (intent) {
            EditContract.Intent.MoveToMainScreen -> {
                viewModelScope.launch {
                    direction.back()
                }
            }

            is EditContract.Intent.EditContact -> {
                uiState.update { it.copy(isSuccess = false) }
                viewModelScope.launch {
                    appRepository.editContact(
                        intent.contactData.id,
                        intent.contactData.name,
                        intent.contactData.phone,
                        intent.contactData.image.toUri()
                    ).onEach {
                        uiState.update { it.copy(isSuccess = true) }
                        if (it) {
                            direction.back()
                        }
                    }.collect()
                }

            }

        }

    }

    private fun reduce(block: (oldState: EditContract.UiState) -> EditContract.UiState) {
        val old = uiState.value
        uiState.value = block(old)
    }
}