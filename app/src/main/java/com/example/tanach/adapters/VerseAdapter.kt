package com.example.tanach.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tanach.databinding.ItemVerseBinding
import com.example.tanach.models.Verse

class VerseAdapter(private val onClick: (Verse) -> Unit) : RecyclerView.Adapter<VerseAdapter.VerseViewHolder>() {
    
    private var verses: List<Verse> = emptyList()
    
    fun submitList(newVerses: List<Verse>) {
        verses = newVerses
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseViewHolder {
        val binding = ItemVerseBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return VerseViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: VerseViewHolder, position: Int) {
        holder.bind(verses[position])
    }
    
    override fun getItemCount(): Int = verses.size
    
    inner class VerseViewHolder(private val binding: ItemVerseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(verse: Verse) {
            binding.textViewVerseNumber.text = verse.verse_number.toString()
            binding.textViewVerseText.text = verse.text
            binding.textViewVerseHebrew.text = verse.hebrew_text ?: ""
            
            binding.root.setOnClickListener { onClick(verse) }
        }
    }
}
