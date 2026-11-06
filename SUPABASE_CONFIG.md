# Supabase Configuration for Tanach App

## Extracted Configuration

From the website `tanach.coolsplash.com`, the following Supabase configuration was extracted:

### Supabase URL
```
https://hflmtveqikurcmzxsqsm.supabase.co
```

### Anonymous Key
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhmbG10dmVxaWt1cmNtenhzcXNtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzM5ODY0ODcsImV4cCI6MjA4OTU2MjQ4N30.cS0WMup7bFQqn2SHwPiltVqzgg1ceoDsQZil_BfaWFA
```

## API Endpoints

The standard Supabase REST endpoints for this project are:

### REST API
- **Base URL**: `https://hflmtveqikurcmzxsqsm.supabase.co/rest/v1/`
- **Headers**: 
  - `apikey`: [anon key above]
  - `Authorization`: `Bearer [anon key above]`
  - `Content-Type`: `application/json`

### GraphQL API
- **URL**: `https://hflmtveqikurcmzxsqsm.supabase.co/graphql/v1`
- **Headers**: Same as REST API

### Realtime API
- **URL**: `https://hflmtveqikurcmzxsqsm.supabase.co/realtime/v1`
- **Headers**: Same as REST API

## Database Tables

Based on the app structure, the following tables are expected:

### 1. books
- `id` (integer, primary key)
- `name` (text) - English name of the book
- `hebrew_name` (text) - Hebrew name of the book
- `description` (text, nullable)
- `chapters_count` (integer)
- `order` (integer)

### 2. chapters
- `id` (integer, primary key)
- `book_id` (integer, foreign key references books.id)
- `chapter_number` (integer)
- `verses_count` (integer, nullable)

### 3. verses
- `id` (integer, primary key)
- `chapter_id` (integer, foreign key references chapters.id)
- `book_id` (integer, optional)
- `chapter_number` (integer, optional)
- `verse_number` (integer)
- `text` (text) - English translation
- `hebrew_text` (text, nullable) - Original Hebrew text
- `translation` (text, nullable)

## Example API Requests

### Get All Books
```bash
curl 'https://hflmtveqikurcmzxsqsm.supabase.co/rest/v1/books' \
  -H "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhmbG10dmVxaWt1cmNtenhzcXNtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzM5ODY0ODcsImV4cCI6MjA4OTU2MjQ4N30.cS0WMup7bFQqn2SHwPiltVqzgg1ceoDsQZil_BfaWFA" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhmbG10dmVxaWt1cmNtenhzcXNtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzM5ODY0ODcsImV4cCI6MjA4OTU2MjQ4N30.cS0WMup7bFQqn2SHwPiltVqzgg1ceoDsQZil_BfaWFA"
```

### Get Chapters for a Book
```bash
curl 'https://hflmtveqikurcmzxsqsm.supabase.co/rest/v1/chapters?book_id=eq.1' \
  -H "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhmbG10dmVxaWt1cmNtenhzcXNtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzM5ODY0ODcsImV4cCI6MjA4OTU2MjQ4N30.cS0WMup7bFQqn2SHwPiltVqzgg1ceoDsQZil_BfaWFA" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhmbG10dmVxaWt1cmNtenhzcXNtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzM5ODY0ODcsImV4cCI6MjA4OTU2MjQ4N30.cS0WMup7bFQqn2SHwPiltVqzgg1ceoDsQZil_BfaWFA"
```

### Get Verses for a Chapter
```bash
curl 'https://hflmtveqikurcmzxsqsm.supabase.co/rest/v1/verses?chapter_id=eq.1' \
  -H "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhmbG10dmVxaWt1cmNtenhzcXNtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzM5ODY0ODcsImV4cCI6MjA4OTU2MjQ4N30.cS0WMup7bFQqn2SHwPiltVqzgg1ceoDsQZil_BfaWFA" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhmbG10dmVxaWt1cmNtenhzcXNtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzM5ODY0ODcsImV4cCI6MjA4OTU2MjQ4N30.cS0WMup7bFQqn2SHwPiltVqzgg1ceoDsQZil_BfaWFA"
```

### Search Verses
```bash
curl 'https://hflmtveqikurcmzxsqsm.supabase.co/rest/v1/verses?text=ilike.*%25search_term%25*' \
  -H "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhmbG10dmVxaWt1cmNtenhzcXNtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzM5ODY0ODcsImV4cCI6MjA4OTU2MjQ4N30.cS0WMup7bFQqn2SHwPiltVqzgg1ceoDsQZil_BfaWFA" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhmbG10dmVxaWt1cmNtenhzcXNtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzM5ODY0ODcsImV4cCI6MjA4OTU2MjQ4N30.cS0WMup7bFQqn2SHwPiltVqzgg1ceoDsQZil_BfaWFA"
```

## Security Note

⚠️ **IMPORTANT**: The anonymous key is publicly accessible and meant for client-side use. However:

1. **Do NOT expose this key in server-side code**
2. **Do NOT commit this key to public repositories** (it's already in the app code)
3. **Consider using row-level security (RLS)** in Supabase to protect sensitive data
4. **For production apps**, consider using a backend service to proxy requests

## Decoded Anon Key

The JWT anon key decodes to:
```json
{
  "alg": "HS256",
  "typ": "JWT"
}
{
  "iss": "supabase",
  "ref": "hflmtveqikurcmzxsqsm",
  "role": "anon",
  "iat": 1773986487,
  "exp": 2089562487
}
```

- **Project Reference**: hflmtveqikurcmzxsqsm
- **Role**: anon (anonymous/public access)
- **Issued At**: 1773986487 (timestamp)
- **Expires**: 2089562487 (timestamp - far in the future)
