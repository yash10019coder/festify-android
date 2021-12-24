package com.iiitlucknow.android.festify.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words_table")
data class my_events(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val my_img: Int,
    val my_title: Int,
    val date: Int
)
