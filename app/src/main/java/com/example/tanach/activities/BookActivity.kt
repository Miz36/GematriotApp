package com.example.tanach.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tanach.adapters.ChapterAdapter
import com.example.tanach.databinding.ActivityBookBinding
import com.example.tanach.models.Chapter
import com.example.tanach.network.TanachRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityBookBinding
    private lateinit var chapterAdapter: ChapterAdapter
    private val repository = TanachRepository()
    private var bookId: Int = 0
    private var chaptersCount: Int = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        bookId = intent.getIntExtra("BOOK_ID", 0)
        val bookName = intent.getStringExtra("BOOK_NAME") ?: "Book"
        val hebrewName = intent.getStringExtra("BOOK_HEBREW_NAME")
        chaptersCount = intent.getIntExtra("BOOK_CHAPTERS", 0)
        
        binding.toolbar.title = "$bookName ${hebrewName?.let { "($it)" } ?: ""}"
        binding.toolbar.setNavigationOnClickListener { finish() }
        
        setupRecyclerView()
        loadChapters()
    }
    
    private fun setupRecyclerView() {
        chapterAdapter = ChapterAdapter { chapter ->
            val intent = Intent(this, ChapterActivity::class.java).apply {
                putExtra("BOOK_ID", bookId)
                putExtra("BOOK_NAME", intent.getStringExtra("BOOK_NAME"))
                putExtra("CHAPTER_NUMBER", chapter.chapter_number)
                putExtra("CHAPTER_ID", chapter.id)
            }
            startActivity(intent)
        }
        
        binding.recyclerViewChapters.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewChapters.adapter = chapterAdapter
    }
    
    private fun loadChapters() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val chapters = repository.getChaptersByBook(bookId)
                withContext(Dispatchers.Main) {
                    if (chapters.isEmpty() && chaptersCount > 0) {
                        // Generate chapters if not in database
                        val generatedChapters = (1..chaptersCount).map { chapterNum ->
                            Chapter(null, bookId, chapterNum, null)
                        }
                        chapterAdapter.submitList(generatedChapters)
                    } else {
                        chapterAdapter.submitList(chapters)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Fallback: generate chapters based on chaptersCount
                withContext(Dispatchers.Main) {
                    val generatedChapters = (1..chaptersCount).map { chapterNum ->
                        Chapter(null, bookId, chapterNum, null)
                    }
                    chapterAdapter.submitList(generatedChapters)
                }
            }
        }
    }
}
