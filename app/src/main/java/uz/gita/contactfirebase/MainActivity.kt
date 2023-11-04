package uz.gita.contactfirebase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.contactfirebase.navigation.AppNavigationHandler
import uz.gita.contactfirebase.presentation.main.MainScreen
import uz.gita.contactfirebase.ui.theme.ContactFireBaseTheme
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appNavigationHandler: AppNavigationHandler
    @SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactFireBaseTheme {
                Navigator(
                    screen = MainScreen()
                ) { navigator ->
                    appNavigationHandler.uiNavigator
                        .onEach { it.invoke(navigator) }
                        .launchIn(lifecycleScope)

                    CurrentScreen()
                }

            }
        }
    }
}
