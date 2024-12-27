package com.example.smartlab.dataClasses

data class auth_event(
    val actor_id: String
)

data class ResponseReg(
    val auth_event: List<auth_event>
)
