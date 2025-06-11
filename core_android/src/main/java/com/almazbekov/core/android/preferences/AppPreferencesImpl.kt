@file:Suppress("ForbiddenImport")
package com.almazbekov.core.android.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.almazbekov.core.preferences.AppPreferences

@Suppress("TooManyFunctions")
class AppPreferencesImpl(
    context: Context,
    name: String,
) : AppPreferences {

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    override fun getString(key: String, defaultValue: String): String {
        return sharedPrefs.getString(key, defaultValue) ?: defaultValue
    }

    override fun setString(key: String, value: String) {
        sharedPrefs.edit { putString(key, value) }
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPrefs.getBoolean(key, defaultValue)
    }

    override fun setBoolean(key: String, value: Boolean) {
        sharedPrefs.edit { putBoolean(key, value) }
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return sharedPrefs.getInt(key, defaultValue)
    }

    override fun setInt(key: String, value: Int) {
        sharedPrefs.edit { putInt(key, value) }
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return sharedPrefs.getLong(key, defaultValue)
    }

    override fun setLong(key: String, value: Long) {
        sharedPrefs.edit { putLong(key, value) }
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPrefs.getFloat(key, defaultValue)
    }

    override fun setFloat(key: String, value: Float) {
        sharedPrefs.edit { putFloat(key, value) }
    }

    override fun remove(key: String) {
        sharedPrefs.edit { remove(key) }
    }

    override fun clear() {
        sharedPrefs.edit { clear() }
    }

    override fun contains(key: String): Boolean {
        return sharedPrefs.contains(key)
    }
}
