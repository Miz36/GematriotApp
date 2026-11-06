package com.example.tanach.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: Int? = null,
    val name: String,
    val hebrew_name: String? = null,
    val description: String? = null,
    val chapters_count: Int? = null,
    val order: Int? = null
)
