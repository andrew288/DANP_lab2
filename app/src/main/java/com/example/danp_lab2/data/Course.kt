package com.example.danp_lab2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course (
    @PrimaryKey(autoGenerate = true)
    val courseId : Int,
    val courseName: String
)