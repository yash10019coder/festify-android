package com.iiitlucknow.android.festify.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words_table")
data class my_events(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val my_img: Int,
    val my_title: String,
    val date: String,
    val desc: String,
    val category: String
)
