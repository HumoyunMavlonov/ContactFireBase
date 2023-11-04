package uz.gita.contactfirebase.navigation

import cafe.adriel.voyager.androidx.AndroidScreen

typealias MyScreen = AndroidScreen

interface AppNavigator {

    suspend fun openScreenSaveStack(screen: MyScreen)
    suspend fun openScreenWithoutSave(screen: MyScreen)
    suspend fun back()
    suspend fun backUntil(screen: MyScreen)
    suspend fun backToRoot()

}