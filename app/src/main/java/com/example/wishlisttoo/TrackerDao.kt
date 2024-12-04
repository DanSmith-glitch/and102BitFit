package com.example.wishlisttoo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {
    @Query("SELECT * FROM tracker_table")
    fun getAll(): Flow<List<TrackerEntity>>

    @Insert
    fun insertAll(wishlistitems: List<TrackerEntity>)

    @Insert
    fun insert(trackers: TrackerEntity)

    @Query("DELETE FROM tracker_table")
    fun deleteAll()

}