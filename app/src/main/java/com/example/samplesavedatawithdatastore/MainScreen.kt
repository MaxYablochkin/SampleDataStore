package com.example.samplesavedatawithdatastore

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val nestedScroll = TopAppBarDefaults.pinnedScrollBehavior()
    val context = LocalContext.current
    val dataStoreContact = DataStoreContact(context)
    val contacts = remember { mutableStateListOf<Contact>() }

    Scaffold(
        modifier = Modifier.nestedScroll(connection = nestedScroll.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.top_app_bar_title)) },
                scrollBehavior = nestedScroll
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.AddContactScreen.toString()) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.fab_btn_description)
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
        ) {
            item {
                LaunchedEffect(key1 = true) {
                    dataStoreContact.getContacts().collect { contact ->
                        if (contact.firstName.isNotEmpty() || contact.lastName.isNotEmpty()) {
                            contacts.add(contact)
                        }
                    }
                }
            }

            items(contacts) { contact ->
                ContactItem(firstName = contact.firstName, lastName = contact.lastName)
            }
        }
    }
}