package com.example.tanach.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tanach.databinding.ItemChapterBinding
import com.example.tanach.models.Chapter

class ChapterAdapter(private val onClick: (Chapter) -> Unit) : RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>() {
    
    private var chapters: List<Chapter> = emptyList()
    
    fun submitList(newChapters: List<Chapter>) {
        chapters = newChapters
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val binding = ItemChapterBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return ChapterViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        holder.bind(chapters[position])
    }
    
    override fun getItemCount(): Int = chapters.size
    
    inner class ChapterViewHolder(private val binding: ItemChapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chapter: Chapter) {
            binding.textViewChapterNumber.text = "Chapter ${chapter.chapter_number}"
            binding.textViewVersesCount.text = "${chapter.verses_count ?: 0} verses"
            
            binding.root.setOnClickListener { onClick(chapter) }
        }
    }
}
