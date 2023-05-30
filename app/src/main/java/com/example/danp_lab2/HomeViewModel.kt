package com.example.danp_lab2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.danp_lab2.data.AppDatabase
import com.example.danp_lab2.data.Course
import com.example.danp_lab2.data.Repository
import com.example.danp_lab2.data.Student
import com.example.danp_lab2.data.relations.StudentCourseCrossRef
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val allCourseData : LiveData<List<Course>>
    private val allStudentData : LiveData<List<Student>>
    private val allStudentCourseCrossRef : LiveData<List<StudentCourseCrossRef>>
    private val repository : Repository
    init {
        val database = AppDatabase.getInstance(application)
        val courseDao = database.courseDao
        val studentDao = database.studentDao
        repository = Repository(courseDao, studentDao)
        allCourseData = repository.allCourse
        allStudentData = repository.allStudent
        allStudentCourseCrossRef = repository.allCourseWithStudent
    }

    fun getAllStudentCourseCrossRef(): LiveData<List<StudentCourseCrossRef>>{
        return allStudentCourseCrossRef
    }

    fun getAllCourse(): LiveData<List<Course>> {
        return allCourseData
    }
    fun addCourse(course: Course){
        viewModelScope.launch {
            repository.addCourse(course)
        }
    }

    fun addStudentCourseCrossRef(crossRef: StudentCourseCrossRef ){
        viewModelScope.launch {
            repository.addStudentCourseCrossRef(crossRef)
        }
    }

    fun getAllStudent() : LiveData<List<Student>> {
        return allStudentData
    }
    fun addStudent(student: Student){
        viewModelScope.launch {
            repository.addStudent(student)
        }
    }


}

