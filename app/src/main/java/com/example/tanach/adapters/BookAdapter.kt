package com.example.tanach.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tanach.databinding.ItemBookBinding
import com.example.tanach.models.Book

class BookAdapter(private val onClick: (Book) -> Unit) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    
    private var books: List<Book> = emptyList()
    
    fun submitList(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return BookViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }
    
    override fun getItemCount(): Int = books.size
    
    inner class BookViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.textViewBookName.text = book.name
            binding.textViewHebrewName.text = book.hebrew_name ?: ""
            binding.textViewChaptersCount.text = "${book.chapters_count ?: 0} chapters"
            
            binding.root.setOnClickListener { onClick(book) }
        }
    }
}
