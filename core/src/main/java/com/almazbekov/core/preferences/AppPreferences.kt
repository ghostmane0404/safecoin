package com.almazbekov.core.preferences

@Suppress("TooManyFunctions")
interface AppPreferences {
    fun getString(key: String, defaultValue: String = ""): String
    fun setString(key: String, value: String)

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean
    fun setBoolean(key: String, value: Boolean)

    fun getInt(key: String, defaultValue: Int = 0): Int
    fun setInt(key: String, value: Int)

    fun getLong(key: String, defaultValue: Long = 0L): Long
    fun setLong(key: String, value: Long)

    fun getFloat(key: String, defaultValue: Float = 0f): Float
    fun setFloat(key: String, value: Float)

    fun remove(key: String)
    fun clear()

    fun contains(key: String): Boolean
}
