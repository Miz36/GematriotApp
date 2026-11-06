package com.example.tanach.network

import android.content.Context
import io.github.supabase_community.supabase_kt.SupabaseClient
import io.github.supabase_community.supabase_kt.createSupabaseClient
import io.github.supabase_community.supabase_kt.postgrest.Postgrest
import io.github.supabase_community.supabase_kt.postgrest.postgrest

object SupabaseClient {
    private const val SUPABASE_URL = "https://hflmtveqikurcmzxsqsm.supabase.co"
    private const val SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhmbG10dmVxaWt1cmNtenhzcXNtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzM5ODY0ODcsImV4cCI6MjA4OTU2MjQ4N30.cS0WMup7bFQqn2SHwPiltVqzgg1ceoDsQZil_BfaWFA"
    
    lateinit var client: SupabaseClient
    lateinit var postgrest: Postgrest
    
    fun init(context: Context) {
        client = createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_ANON_KEY
        ) {
            install(Postgrest)
        }
        postgrest = client.postgrest
    }
}
