package com.example.smartlab.code

import java.util.regex.Pattern

fun isValidDate(date: String): Boolean {
    val emailRegex = """^[0-1][0-9]\.[0-1][0-9]\.[1-2][0-9]{3}$"""
    return Pattern.compile(emailRegex).matcher(date).matches()
}