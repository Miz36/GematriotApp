package com.example.tanach.network

import com.example.tanach.models.Book
import com.example.tanach.models.Chapter
import com.example.tanach.models.Verse
import io.github.supabase_community.supabase_kt.postgrest.from
import io.github.supabase_community.supabase_kt.postgrest.rpc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TanachRepository {
    
    suspend fun getAllBooks(): List<Book> = withContext(Dispatchers.IO) {
        try {
            SupabaseClient.postgrest.from("books").select().decodeList<Book>()
        } catch (e: Exception) {
            listOf(
                Book(1, "Genesis", "בראשית", "The first book of Moses", 50, 1),
                Book(2, "Exodus", "שמות", "The second book of Moses", 40, 2),
                Book(3, "Leviticus", "ויקרא", "The third book of Moses", 27, 3),
                Book(4, "Numbers", "במדבר", "The fourth book of Moses", 36, 4),
                Book(5, "Deuteronomy", "דברים", "The fifth book of Moses", 34, 5),
                Book(6, "Joshua", "יהושע", null, 24, 6),
                Book(7, "Judges", "שופטים", null, 21, 7),
                Book(8, "Samuel 1", "שמואל א", null, 31, 8),
                Book(9, "Samuel 2", "שמואל ב", null, 24, 9),
                Book(10, "Kings 1", "מלכים א", null, 22, 10),
                Book(11, "Kings 2", "מלכים ב", null, 25, 11),
                Book(12, "Isaiah", "ישעיהו", null, 66, 12),
                Book(13, "Jeremiah", "ירמיהו", null, 52, 13),
                Book(14, "Ezekiel", "יחזקאל", null, 48, 14),
                Book(15, "Hosea", "הושע", null, 14, 15),
                Book(16, "Joel", "יואל", null, 3, 16),
                Book(17, "Amos", "עמוס", null, 9, 17),
                Book(18, "Obadiah", "עובדיה", null, 1, 18),
                Book(19, "Jonah", "יונה", null, 4, 19),
                Book(20, "Micah", "מיכה", null, 7, 20),
                Book(21, "Nahum", "נחום", null, 3, 21),
                Book(22, "Habakkuk", "חבקוק", null, 3, 22),
                Book(23, "Zephaniah", "צפניה", null, 3, 23),
                Book(24, "Haggai", "חגי", null, 2, 24),
                Book(25, "Zechariah", "זכריה", null, 14, 25),
                Book(26, "Malachi", "מלאכי", null, 3, 26),
                Book(27, "Psalms", "תהלים", null, 150, 27),
                Book(28, "Proverbs", "משלי", null, 31, 28),
                Book(29, "Job", "איוב", null, 42, 29),
                Book(30, "Song of Songs", "שיר השירים", null, 8, 30),
                Book(31, "Ruth", "רות", null, 4, 31),
                Book(32, "Lamentations", "איכה", null, 5, 32),
                Book(33, "Ecclesiastes", "קהלת", null, 12, 33),
                Book(34, "Esther", "אסתר", null, 10, 34),
                Book(35, "Daniel", "דניאל", null, 12, 35),
                Book(36, "Ezra", "עזרא", null, 10, 36),
                Book(37, "Nehemiah", "נחמיה", null, 13, 37),
                Book(38, "Chronicles 1", "דברי הימים א", null, 29, 38),
                Book(39, "Chronicles 2", "דברי הימים ב", null, 36, 39)
            )
        }
    }
    
    suspend fun getChaptersByBook(bookId: Int): List<Chapter> = withContext(Dispatchers.IO) {
        try {
            SupabaseClient.postgrest.from("chapters").select { 
                filter { eq("book_id", bookId) }
                order("chapter_number", ascending = true)
            }.decodeList<Chapter>()
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun getVersesByChapter(chapterId: Int): List<Verse> = withContext(Dispatchers.IO) {
        try {
            SupabaseClient.postgrest.from("verses").select { 
                filter { eq("chapter_id", chapterId) }
                order("verse_number", ascending = true)
            }.decodeList<Verse>()
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun getVersesByBookAndChapter(bookId: Int, chapterNumber: Int): List<Verse> = withContext(Dispatchers.IO) {
        try {
            SupabaseClient.postgrest.from("verses").select { 
                filter { 
                    eq("book_id", bookId)
                    eq("chapter_number", chapterNumber)
                }
                order("verse_number", ascending = true)
            }.decodeList<Verse>()
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun searchVerses(query: String): List<Verse> = withContext(Dispatchers.IO) {
        try {
            SupabaseClient.postgrest.from("verses").select { 
                filter { ilike("text", "%$query%") }
                limit(50)
            }.decodeList<Verse>()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
