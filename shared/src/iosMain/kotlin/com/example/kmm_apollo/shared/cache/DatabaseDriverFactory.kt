package com.example.kmm_apollo.shared.cache

import com.example.justdesserts.shared.cache.JustDesserts
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(JustDesserts.Schema, "desserts.db")
    }
}