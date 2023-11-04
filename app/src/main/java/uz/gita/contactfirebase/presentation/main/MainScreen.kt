package uz.gita.contactfirebase.presentation.main

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.launch
import uz.gita.contactfirebase.ui.theme.ContactFireBaseTheme
import uz.gita.contactfirebase.presentation.main.impl.MainScreenViewModelImpl
import uz.gita.contactfirebase.presentation.components.ContactItem

class MainScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel = getViewModel<MainScreenViewModelImpl>()

        ContactFireBaseTheme {
            viewModel.loadData()

            MainContent(
                uiState = viewModel.uiState.collectAsState(),
                onEventDispatcher = viewModel::onEventDispatcher
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(
    uiState: State<MainScreenContract.UiState>,
    onEventDispatcher: (MainScreenContract.Intent) -> Unit
) {

    onEventDispatcher.invoke(MainScreenContract.Intent.load)
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF1C1D1B))
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxWidth()
                    .height(70.dp)
            ) {


                Text(
                    text = "All Contacts:",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Add",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .combinedClickable(onClick = {
                            onEventDispatcher.invoke(MainScreenContract.Intent.ClickAdd)
                        }),
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )


            }
            LazyColumn(content = {
                items(uiState.value.allContacts) { data ->
                    ContactItem(
                        contactData = data,
                        onClick = {
                            onEventDispatcher.invoke(MainScreenContract.Intent.ClickEdit(data))
                            onEventDispatcher.invoke(MainScreenContract.Intent.load)
                        },
                        onLongClick = {
                            onEventDispatcher.invoke(MainScreenContract.Intent.ClickDelete(data))
                            onEventDispatcher.invoke(MainScreenContract.Intent.load)
                        }
                    )
                }
            })
        }
    }

}