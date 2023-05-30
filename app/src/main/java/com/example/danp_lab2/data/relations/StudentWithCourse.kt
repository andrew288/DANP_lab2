package com.example.danp_lab2.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.danp_lab2.data.Course
import com.example.danp_lab2.data.Student

data class StudentWithCourse(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "courseId",
        associateBy = Junction(StudentCourseCrossRef::class)
    )
    val course: List<Course>
)