package com.vasili.moviestestapp.common

import androidx.lifecycle.LiveData
import androidx.room.*

@Database(entities = [SearchRequest::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao
}

@Dao
interface SearchDao {

    @Query("SELECT * from searchrequest")
    fun getAll(): LiveData<List<SearchRequest>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(searchRequest: SearchRequest)
}