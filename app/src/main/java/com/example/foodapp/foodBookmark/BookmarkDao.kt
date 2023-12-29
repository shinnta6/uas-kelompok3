package com.example.foodapp.foodBookmark

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface BookmarkDao {
    @Insert
    fun addToBookmark(bookmark: Bookmark)

    @Query("SELECT * FROM bookmarkMeal")
    fun getBookmark():LiveData<List<Bookmark>>

    @Query("SELECT * FROM bookmarkMeal where idMeal=:idMeal")
    fun getBookmarkId(idMeal:String):Bookmark?

    @Query("DELETE FROM bookmarkMeal where idMeal=:idMeal")
    fun deleteBookmark(idMeal: String):Int
}