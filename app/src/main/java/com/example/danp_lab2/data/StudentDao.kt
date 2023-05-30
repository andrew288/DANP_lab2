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
import com.example.danp_lab2.data.relations.StudentWithCourse

@Dao
interface StudentDao {
    @Query("SELECT * from Student")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT * from Student WHERE StudentId = :studentId")
    fun getStudent(studentId: Int): Student?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Transaction
    @Query("SELECT * FROM Student WHERE StudentId = :StudentId")
    fun getStudentWithCourse(StudentId : Int): LiveData<List<StudentWithCourse>>

}