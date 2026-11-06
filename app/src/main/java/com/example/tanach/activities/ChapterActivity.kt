package com.example.tanach.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tanach.adapters.VerseAdapter
import com.example.tanach.databinding.ActivityChapterBinding
import com.example.tanach.models.Verse
import com.example.tanach.network.TanachRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChapterActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityChapterBinding
    private lateinit var verseAdapter: VerseAdapter
    private val repository = TanachRepository()
    private var bookId: Int = 0
    private var chapterNumber: Int = 0
    private var chapterId: Int? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChapterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        bookId = intent.getIntExtra("BOOK_ID", 0)
        chapterNumber = intent.getIntExtra("CHAPTER_NUMBER", 0)
        chapterId = intent.getIntExtra("CHAPTER_ID", -1).takeIf { it != -1 }
        val bookName = intent.getStringExtra("BOOK_NAME") ?: "Book"
        
        binding.toolbar.title = "$bookName $chapterNumber"
        binding.toolbar.setNavigationOnClickListener { finish() }
        
        setupRecyclerView()
        loadVerses()
    }
    
    private fun setupRecyclerView() {
        verseAdapter = VerseAdapter { verse ->
            val intent = Intent(this, VerseActivity::class.java).apply {
                putExtra("VERSE_TEXT", verse.text)
                putExtra("VERSE_HEBREW", verse.hebrew_text)
                putExtra("VERSE_NUMBER", verse.verse_number)
                putExtra("BOOK_NAME", intent.getStringExtra("BOOK_NAME"))
                putExtra("CHAPTER_NUMBER", chapterNumber)
            }
            startActivity(intent)
        }
        
        binding.recyclerViewVerses.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewVerses.adapter = verseAdapter
    }
    
    private fun loadVerses() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val verses = if (chapterId != null) {
                    repository.getVersesByChapter(chapterId!!)
                } else {
                    repository.getVersesByBookAndChapter(bookId, chapterNumber)
                }
                withContext(Dispatchers.Main) {
                    verseAdapter.submitList(verses)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
