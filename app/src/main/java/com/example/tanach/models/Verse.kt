package com.example.tanach.models

import kotlinx.serialization.Serializable

@Serializable
data class Verse(
    val id: Int? = null,
    val chapter_id: Int,
    val verse_number: Int,
    val text: String,
    val hebrew_text: String? = null,
    val translation: String? = null
)
