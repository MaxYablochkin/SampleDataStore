package com.example.samplesavedatawithdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class DataStoreContact(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Contacts")
        val FIRST_NAME_KEY = stringPreferencesKey("first_name")
        val LAST_NAME_KEY = stringPreferencesKey("last_name")

    }

    suspend fun saveContact(contactInfo: Contact) {
        context.dataStore.edit { preferences ->
            preferences[FIRST_NAME_KEY] = contactInfo.firstName
            preferences[LAST_NAME_KEY] = contactInfo.lastName
        }
    }

    fun getContacts() = context.dataStore.data.map { preferences ->
        return@map Contact(
            preferences[FIRST_NAME_KEY] ?: "",
            preferences[LAST_NAME_KEY] ?: ""
        )
    }
}