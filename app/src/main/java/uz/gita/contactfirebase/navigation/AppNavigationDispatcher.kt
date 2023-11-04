package uz.gita.contactfirebase.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigationDispatcher @Inject constructor() : AppNavigator, AppNavigationHandler {

    override val uiNavigator = MutableSharedFlow<NavigationArgs>()

    private suspend fun myNavigate(navigator: NavigationArgs) {
        uiNavigator.emit(navigator)
    }

    override suspend fun openScreenSaveStack(screen: MyScreen) = myNavigate { push(screen) }

    override suspend fun openScreenWithoutSave(screen: MyScreen) = myNavigate { replace(screen) }

    override suspend fun back() = myNavigate { pop() }

    override suspend fun backUntil(screen: MyScreen) = myNavigate { popUntil { it == screen } }

    override suspend fun backToRoot() = myNavigate { popUntilRoot() }

}