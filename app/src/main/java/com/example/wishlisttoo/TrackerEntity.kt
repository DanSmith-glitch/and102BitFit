package com.example.wishlisttoo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracker_table")
data class TrackerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "itemf") val itemf: String?,
    @ColumnInfo(name = "calos") val calos: String?
)