package com.example.danp_lab2.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.danp_lab2.HomeViewModel
import com.example.danp_lab2.data.Course

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LisCourses(viewModel: HomeViewModel) {

    var listStudent = viewModel.getAllStudent().observeAsState().value
    var listCourse = viewModel.getAllCourse().observeAsState().value
    var listStudentCourseCrossRef = viewModel.getAllStudentCourseCrossRef().observeAsState().value

    LazyColumn() {
        if (listStudent != null && listCourse != null && listStudentCourseCrossRef != null) {
            listCourse.forEach {
                var aux = it
                item {
                    Row() {
                        Text(text = it.courseName + " : ")
                        listStudent.forEach {
                            var aux2 = it
                            listStudentCourseCrossRef.forEach {
                                if (it.courseId == aux.courseId && it.studentId == aux2.studentId) {
                                    Text(text = "-->"+aux2.studentName)
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}




