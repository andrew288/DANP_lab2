package com.example.danp_lab2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = false)
    val studentId: Int,
    val studentName: String,
    val studentCod: String,
)
