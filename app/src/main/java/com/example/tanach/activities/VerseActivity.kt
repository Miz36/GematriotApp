package com.example.tanach.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tanach.databinding.ActivityVerseBinding

class VerseActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityVerseBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val verseText = intent.getStringExtra("VERSE_TEXT") ?: ""
        val verseHebrew = intent.getStringExtra("VERSE_HEBREW")
        val verseNumber = intent.getIntExtra("VERSE_NUMBER", 0)
        val bookName = intent.getStringExtra("BOOK_NAME") ?: ""
        val chapterNumber = intent.getIntExtra("CHAPTER_NUMBER", 0)
        
        binding.toolbar.title = "$bookName $chapterNumber:$verseNumber"
        binding.toolbar.setNavigationOnClickListener { finish() }
        
        binding.textViewVerseNumber.text = verseNumber.toString()
        binding.textViewVerseText.text = verseText
        binding.textViewVerseHebrew.text = verseHebrew ?: ""
        binding.textViewReference.text = "$bookName $chapterNumber:$verseNumber"
    }
}
