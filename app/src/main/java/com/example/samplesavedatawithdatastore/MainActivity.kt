package com.example.samplesavedatawithdatastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.samplesavedatawithdatastore.ui.theme.SampleSaveDataWithDataStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleSaveDataWithDataStoreTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.ContactsScreen.toString()
                ) {
                    composable(route = Screens.ContactsScreen.toString()) {
                        MainScreen(navController)
                    }
                    composable(route = Screens.AddContactScreen.toString()) {
                        AddContactScreen(navController)
                    }
                }
            }
        }
    }
}