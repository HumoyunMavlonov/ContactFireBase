package uz.gita.contactfirebase.presentation.addscreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import uz.gita.contactfirebase.data.UploadData
import uz.gita.contactfirebase.presentation.addscreen.impl.AddScreenViewModelImpl
import uz.gita.contactfirebase.presentation.main.MainScreenContract
import uz.gita.contactfirebase.ui.theme.BaseColor
import uz.gita.contactfirebase.ui.theme.TextFieldColor

class AddScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel = getViewModel<AddScreenViewModelImpl>()
        AddScreenContent(
            uiState =viewModel.uiState.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun AddScreenContent(
    uiState: State<AddScreenContract.UiState>,
    onEventDispatcher: (AddScreenContract.Intent) -> Unit
) {

    var contactName by remember { mutableStateOf("") }
    var contactPhone by remember { mutableStateOf("") }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    val uriParser = Uri.parse(imageUri.toString())

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(50.dp))


        GlideImage(
                model = uriParser,
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)

                .padding(top = 10.dp)
                .height(50.dp)
                .width(50.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(12.dp))
                .clickable { launcher.launch("image/*") }
                .background(Color.LightGray),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(50.dp))

        EditTextField(
            labelText = "Name",
            value = contactName,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChanged = { contactName=it },
            paddingHorizontal = 32.dp,
            maxLines = 20
        )

        Spacer(modifier = Modifier.height(24.dp))

        EditTextField(
            labelText = "Phone",
            value = contactPhone,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done,
                ),
            onValueChanged = { if (INPUT_LENGTH >= it.length){
                contactPhone = it.filter { it.isDigit() }.trim()
            }
                },
            paddingHorizontal = 32.dp,
            maxLines = 9
        )


        Spacer(modifier = Modifier.size(8.dp))

        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            ,
//            enabled = uiState.value.isSucces,
            onClick = {
            onEventDispatcher.invoke(AddScreenContract.Intent.AddContact(contactName,"+998".plus(contactPhone),imageUri!!
            ))
            onEventDispatcher.invoke(AddScreenContract.Intent.MoveToMainScreen)
        }) {
            Text(text = "add")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextField(
    labelText: String,
    value: String,
    maxLines :Int,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    onValueChanged: (String) -> Unit,
    paddingHorizontal: Dp = 0.dp,
) {
    TextField(
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = paddingHorizontal)
            .height(58.dp)
            .fillMaxWidth(),
        placeholder = { Text(text = labelText) },
        keyboardOptions = keyboardOptions,
        value = value,
        maxLines = maxLines,
        onValueChange = onValueChanged,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = TextFieldColor,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            disabledLabelColor = Color.LightGray,
            cursorColor = BaseColor,
        ),
        shape = RoundedCornerShape(5.dp),
        singleLine = true
    )
}

const val INPUT_LENGTH = 13

