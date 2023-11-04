package uz.gita.contactfirebase.presentation.main.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.gita.contactfirebase.domain.usecase.DeleteContact
import uz.gita.contactfirebase.domain.usecase.GetAllUseCase
import uz.gita.contactfirebase.presentation.main.MainScreenContract
import uz.gita.contactfirebase.presentation.main.MainScreenDirection
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor(
    private val direction: MainScreenDirection,
    private val useCase: GetAllUseCase,
    private val useCaseDe:DeleteContact
) : ViewModel(), MainScreenContract.ViewModel {
    override val uiState = MutableStateFlow(MainScreenContract.UiState())


    override fun onEventDispatcher(intent: MainScreenContract.Intent) {
        when (intent) {
            MainScreenContract.Intent.load ->{
                viewModelScope.launch {
                    loadData()
                }
            }
            MainScreenContract.Intent.ClickAdd -> {
                viewModelScope.launch {
                    direction.moveToAdd()
                }
            }

            is MainScreenContract.Intent.ClickEdit -> {
                viewModelScope.launch {
                    direction.moveToEdit(intent.data)
                }
            }

            is MainScreenContract.Intent.ClickDelete -> {
                viewModelScope.launch {
                    useCaseDe.deleteContactUseCase(intent.data.id)

                }

            }
        }
    }

    override fun loadData() {
        useCase.getAllContactUseCase().onEach {
            it.onSuccess { list ->
                reduce {
                    this.copy(list)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun reduce(block: MainScreenContract.UiState.() -> MainScreenContract.UiState) {
        val oldValue = uiState.value
        val newValue = block(oldValue)
        viewModelScope.launch {
            uiState.emit(newValue)
        }
    }

}