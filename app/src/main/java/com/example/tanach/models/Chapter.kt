package com.example.tanach.models

import kotlinx.serialization.Serializable

@Serializable
data class Chapter(
    val id: Int? = null,
    val book_id: Int,
    val chapter_number: Int,
    val verses_count: Int? = null
)
