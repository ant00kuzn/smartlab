package com.example.smartlab.code

import java.util.regex.Pattern

fun isValidEmail(email: String): Boolean {
    val emailRegex = """^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$"""
    return Pattern.compile(emailRegex).matcher(email).matches()
}
