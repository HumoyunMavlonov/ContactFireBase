package uz.gita.contactfirebase.data

import java.io.Serializable

data class ContactData(
    val id:String,
    val name : String,
    val phone : String,
    val image:String
):Serializable
