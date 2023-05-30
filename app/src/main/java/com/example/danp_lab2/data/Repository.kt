package com.example.danp_lab2.data

import androidx.lifecycle.LiveData
import com.example.danp_lab2.data.relations.CourseWithStudent
import com.example.danp_lab2.data.relations.StudentCourseCrossRef

class Repository(private val courseDao: CourseDao, private val studentDao: StudentDao ) {


    val allCourse  : LiveData<List<Course>> = courseDao.getAllCourses()
    val allStudent  : LiveData<List<Student>> = studentDao.getAllStudents()
    val allCourseWithStudent  : LiveData<List<StudentCourseCrossRef>> = courseDao.getAllStudentCourseCrossRef()


    suspend fun addStudentCourseCrossRef(crossRef: StudentCourseCrossRef){
        courseDao.insertStudentCourseCrossRef(crossRef)
    }

    suspend fun addStudent(student: Student ){
        studentDao.insertStudent(student)
    }

    suspend fun  updateItem(student: Student ){
        studentDao.updateStudent(student)
    }

    suspend fun deleteItem(student: Student ){
        studentDao.deleteStudent(student)
    }

    suspend fun addCourse(course: Course){
        courseDao.insertCourse(course)
    }

    suspend fun  updateItem(course: Course){
        courseDao.updateCourse(course)
    }

    suspend fun deleteItem(course: Course){
        courseDao.deleteCourse(course)
    }

}