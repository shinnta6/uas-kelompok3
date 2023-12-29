package com.example.foodapp.foodBookmark

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Bookmark::class], version = 1)
open abstract class BookmarkDB():RoomDatabase() {
    abstract fun bookmarkDao():BookmarkDao
}