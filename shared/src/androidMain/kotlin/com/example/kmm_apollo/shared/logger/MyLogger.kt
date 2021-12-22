package com.example.kmm_apollo.shared.logger

actual class MyLogger {
    actual fun logMessage(message: String) {
        println(message)
    }
}