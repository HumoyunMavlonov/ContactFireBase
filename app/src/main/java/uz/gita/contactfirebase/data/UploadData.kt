package uz.gita.contactfirebase.data

sealed interface UploadData {
     object Success: UploadData
     object Cancel : UploadData
     object Pause : UploadData

    data class Progress(
        val uploadBytes :Long,
        val totalBytes : Long
    ) : UploadData
}