package com.example.foodapp.foodBookmark

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkRepository @Inject constructor(private var bookmarkDao: BookmarkDao) {
    suspend fun addToBookmark(bookmark: Bookmark){
        return bookmarkDao.addToBookmark(bookmark)
    }
    fun getBookmark():LiveData<List<Bookmark>>{
        return bookmarkDao.getBookmark()
    }
    suspend fun getBookmarkId(id:String):Bookmark?{
        return bookmarkDao.getBookmarkId(id)
    }
    suspend fun deleteBookmark(id:String):Int{
        return bookmarkDao.deleteBookmark(id)
    }
}