package com.example.samplesavedatawithdatastore

sealed class Screens(destination: String) {
    object ContactsScreen : Screens("MainScreen")
    object AddContactScreen : Screens("AddContactScreen")
}
