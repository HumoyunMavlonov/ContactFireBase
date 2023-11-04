package uz.gita.contactfirebase.presentation.components

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import uz.gita.contactfirebase.R
import uz.gita.contactfirebase.data.ContactData


@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun ContactItem(
    contactData: ContactData,
    onClick: (ContactData) -> Unit,
    onLongClick : (ContactData) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clip(RoundedCornerShape(15.dp))
            .combinedClickable(onClick = { onClick(contactData) }, onLongClick = {onLongClick(contactData)})
            .background(color = MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(8.dp))

        Column(
            modifier = Modifier
                .weight(1f, true)
        ) {
            val uri= Uri.parse(contactData.image)
            Log.d("TTT", "${uri}: ")
            GlideImage(
                model = uri,
                loading = placeholder(R.drawable.ic_eye),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .height(40.dp)
                    .width(40.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))

            Text(
                text = contactData.name,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.size(4.dp))

            Text(text = contactData.phone,fontSize = 16.sp)

        }
    }
}