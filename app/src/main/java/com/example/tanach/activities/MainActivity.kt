package com.example.tanach.activities

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tanach.adapters.BookAdapter
import com.example.tanach.databinding.ActivityMainBinding
import com.example.tanach.models.Book
import com.example.tanach.network.TanachRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var bookAdapter: BookAdapter
    private val repository = TanachRepository()
    private var allBooks: List<Book> = emptyList()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupRecyclerView()
        setupSearch()
        loadBooks()
    }
    
    private fun setupRecyclerView() {
        bookAdapter = BookAdapter { book ->
            val intent = Intent(this, BookActivity::class.java).apply {
                putExtra("BOOK_ID", book.id)
                putExtra("BOOK_NAME", book.name)
                putExtra("BOOK_HEBREW_NAME", book.hebrew_name)
                putExtra("BOOK_CHAPTERS", book.chapters_count ?: 0)
            }
            startActivity(intent)
        }
        
        binding.recyclerViewBooks.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewBooks.adapter = bookAdapter
    }
    
    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { performSearch(it) }
                return true
            }
            
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    bookAdapter.submitList(allBooks)
                }
                return true
            }
        })
    }
    
    private fun performSearch(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val results = repository.searchVerses(query)
                withContext(Dispatchers.Main) {
                    // For now, just filter books by name
                    val filtered = allBooks.filter {
                        it.name.contains(query, ignoreCase = true) || 
                        it.hebrew_name?.contains(query, ignoreCase = true) == true
                    }
                    bookAdapter.submitList(filtered)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    private fun loadBooks() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val books = repository.getAllBooks()
                withContext(Dispatchers.Main) {
                    allBooks = books
                    bookAdapter.submitList(books)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
