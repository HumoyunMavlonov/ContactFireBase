package uz.gita.contactfirebase.data

import java.io.Serializable

data class ContactRequest(
    val name : String,
    val phone : String,
    val image:String
) :Serializable{
}