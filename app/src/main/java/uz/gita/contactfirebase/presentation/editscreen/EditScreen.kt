package uz.gita.contactfirebase.presentation.editscreen

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import uz.gita.contactfirebase.R
import uz.gita.contactfirebase.data.ContactData
import uz.gita.contactfirebase.presentation.addscreen.EditTextField
import uz.gita.contactfirebase.presentation.editscreen.impl.EditViewModelIMpl

class EditScreen constructor(
    private val contactData: ContactData

) : AndroidScreen() {
    @Composable
    override fun Content() {


            val viewModel : EditContract.ViewModel = getViewModel<EditViewModelIMpl>()
        EditScreenContent(
            contactData,
            viewModel.uiState.collectAsState().value ,
            viewModel::eventDispatchers


        )

    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun EditScreenContent(
    contactData: ContactData,
    uiState : EditContract.UiState ,
    onEventDispatcher : (EditContract.Intent) -> Unit
) {
    val uri=Uri.parse(contactData.image)
    var name by remember {
mutableStateOf(contactData.name)
    }
    var phone by remember {
        mutableStateOf(contactData.phone)
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(uri)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    val uriParsed = Uri.parse(imageUri.toString())

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(50.dp))

        GlideImage(
            model = uriParsed,
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .padding(top = 10.dp)
                .height(50.dp)
                .width(50.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(12.dp))
                .clickable { launcher.launch("image/*") },
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(50.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                ) {


                    EditTextField(
                        labelText = "Name",
                        value = name,
                        onValueChanged = { name = it },
                        paddingHorizontal = 32.dp,
                        maxLines = 20
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    EditTextField(
                        labelText = "Phone",
                        value = phone,
                        onValueChanged = { if (INPUT_LENGTH >= it.length){
                            phone = it.filter { it.isDigit() }.trim()
                        }},
                        paddingHorizontal = 32.dp,
                        maxLines = 13
                    )

                    Button(
                        modifier = Modifier
                            .padding(top = 51.dp)
                            .width(310.dp)
                            .height(50.dp)
                            .align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFEB3B)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            onEventDispatcher.invoke(
                                EditContract.Intent.EditContact(
                                    ContactData(
                                        contactData.id,
                                        name,
                                        phone,
                                        imageUri.toString()
                                    )

                                )
                            )
                            onEventDispatcher.invoke(EditContract.Intent.MoveToMainScreen)

                        }
                    ) {
                        Text(
                            text = "Update",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W400
                            )
                        )
                    }
                }
            }
        }
    }
const val INPUT_LENGTH = 13
