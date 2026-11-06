# Tanach Android App

An Android application for browsing the Tanach (Hebrew Bible) using Supabase as the backend.

## Features

- Browse all books of the Tanach
- Navigate through chapters and verses
- View both English and Hebrew text
- Search functionality
- Clean, modern Material Design UI

## Supabase Configuration

The app connects to the following Supabase instance:

- **URL**: `https://hflmtveqikurcmzxsqsm.supabase.co`
- **Anonymous Key**: `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhmbG10dmVxaWt1cmNtenhzcXNtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzM5ODY0ODcsImV4cCI6MjA4OTU2MjQ4N30.cS0WMup7bFQqn2SHwPiltVqzgg1ceoDsQZil_BfaWFA`

## API Endpoints

The app uses the following Supabase REST endpoints:

- **Books**: `POST /rest/v1/books` - Get all books
- **Chapters**: `POST /rest/v1/chapters` - Get chapters by book
- **Verses**: `POST /rest/v1/verses` - Get verses by chapter or search

## Project Structure

```
tanach-app/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/tanach/
│   │   │   │   ├── activities/      # Activity classes
│   │   │   │   ├── adapters/        # RecyclerView adapters
│   │   │   │   ├── models/          # Data models
│   │   │   │   ├── network/         # Network and Supabase classes
│   │   │   │   └── TanachApplication.kt
│   │   │   └── res/                 # Resources (layouts, drawables, etc.)
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── README.md
```

## Database Schema

The app expects the following tables in Supabase:

### books
- `id` (integer, primary key)
- `name` (text) - English name
- `hebrew_name` (text) - Hebrew name
- `description` (text, optional)
- `chapters_count` (integer)
- `order` (integer)

### chapters
- `id` (integer, primary key)
- `book_id` (integer, foreign key)
- `chapter_number` (integer)
- `verses_count` (integer, optional)

### verses
- `id` (integer, primary key)
- `chapter_id` (integer, foreign key)
- `book_id` (integer, optional)
- `chapter_number` (integer, optional)
- `verse_number` (integer)
- `text` (text) - English text
- `hebrew_text` (text, optional) - Hebrew text
- `translation` (text, optional)

## Building the App

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle
4. Build and run on an Android device or emulator

## Requirements

- Android SDK 34
- Kotlin 1.9.0
- Java 8+

## Dependencies

- AndroidX libraries
- Material Components
- Supabase Kotlin SDK
- Kotlin Coroutines
- Kotlin Serialization

## Customization

To customize the app:

1. **Change Supabase Configuration**: Edit `SupabaseClient.kt`
2. **Modify UI**: Update layout files in `res/layout/`
3. **Add Features**: Extend the repository and activities

## Notes

- The app includes fallback data for books if the Supabase tables don't exist
- Search functionality currently searches by book name and Hebrew name
- Verse display shows both English and Hebrew text when available
