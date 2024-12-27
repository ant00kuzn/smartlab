package com.example.smartlab.dataClasses

data class PatientCard(
    val id: Int,
    val user_id: Int,
    val firstname: String,
    val lastname: String,
    val thirdname: String,
    val birthday: String,
    val gender: Int
)
