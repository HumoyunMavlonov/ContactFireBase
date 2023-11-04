package uz.gita.contactfirebase.data.impl

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.contactfirebase.data.ContactData
import uz.gita.contactfirebase.domain.repository.AppRepository
import uz.gita.contactfirebase.ui.getAll
import uz.gita.contactfirebase.ui.getAllLive
import java.util.UUID
import javax.inject.Inject


class AppRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val firestore: FirebaseFirestore

) : AppRepository {


    private val storageRef = Firebase.storage.reference

    override fun getAllContacts(): Flow<Result<List<ContactData>>> =

        firestore.collection("contacts").getAllLive {
            Log.d("TTT", "getAllContacts: $it")
            ContactData(
                it.id,
                it.data?.getOrDefault("name", "").toString(),
                it.data?.getOrDefault("phone", "").toString(),
                it.data?.getOrDefault("image", "").toString()
            )
        }

    override fun addContacts(name: String, phone: String, image: Uri): Flow<Boolean> =
        callbackFlow {
            val contactId: String = "${UUID.randomUUID()}"
            image.let {
                storageRef.child("images/$contactId").putFile(it)
                    .addOnSuccessListener { task ->
                        task.metadata!!.reference!!.downloadUrl
                            .addOnSuccessListener {
                                firestore.collection("contacts")
                                    .document(contactId)
                                    .set(
                                        ContactData(
                                            contactId,
                                            name,
                                            phone,
                                            it.toString()
                                        )
                                    )
                                    .addOnSuccessListener {
                                        trySend(true)
                                        Toast.makeText(context, "Contact added", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                    .addOnFailureListener {
                                        trySend(false)
                                        Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT)
                                            .show()
                                    }

                            }
                    }
                awaitClose { }

            }
        }


    override fun editContact(id: String, name: String, phone: String, image: Uri): Flow<Boolean> =
        callbackFlow {
            firestore.collection("contacts").document(id)
                .set(ContactData(id, name, phone, image.toString()))
                .addOnSuccessListener {
                    trySend(true)
                }
                .addOnFailureListener {
                    trySend(false)
                }
            awaitClose()
        }

    override fun deleteContact(id: String) {
        firestore.collection("contacts")
            .document(id)
            .delete()
            .addOnSuccessListener {
                storageRef.child("images/$id").delete()
                Toast.makeText(context, "Contact deleted", Toast.LENGTH_SHORT).show()
            }
    }
}


