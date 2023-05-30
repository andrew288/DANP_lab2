package com.example.danp_lab2.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.danp_lab2.data.Course
import com.example.danp_lab2.data.Student

data class CourseWithStudent(
    @Embedded val course: Course,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "studentId",
        associateBy = Junction(StudentCourseCrossRef::class)
    )
    val student: List<Student>
)