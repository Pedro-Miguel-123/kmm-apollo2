package com.example.kmm_apollo.shared.cache

import android.content.Context
import com.example.justdesserts.shared.cache.JustDesserts
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(JustDesserts.Schema, context, "desserts.db")
    }
}