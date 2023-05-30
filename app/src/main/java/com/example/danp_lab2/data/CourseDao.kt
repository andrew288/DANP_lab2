package com.example.danp_lab2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.danp_lab2.data.relations.CourseWithStudent
import com.example.danp_lab2.data.relations.StudentCourseCrossRef
import com.example.danp_lab2.data.relations.StudentWithCourse



@Dao
interface CourseDao {
    @Query("SELECT * from Course")
    fun getAllCourses(): LiveData<List<Course>>

    @Query("SELECT * from Course WHERE courseId = :courseId")
    fun getCourse(courseId: Int): Course?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCourse(course: Course)

    @Update
    suspend fun updateCourse(course: Course)

    @Delete
    suspend fun deleteCourse(course: Course)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudentCourseCrossRef(crossRef: StudentCourseCrossRef)

    @Query("SELECT * from StudentCourseCrossRef")
    fun getAllStudentCourseCrossRef() : LiveData<List<StudentCourseCrossRef>>

    @Transaction
    @Query("SELECT * FROM Course WHERE courseId = :courseId")
    fun getCourseWithStudent(courseId : Int): LiveData<List<CourseWithStudent>>

}