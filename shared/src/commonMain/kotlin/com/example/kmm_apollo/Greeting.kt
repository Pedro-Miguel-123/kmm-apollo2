package com.example.kmm_apollo

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}