package com.example.danp_lab2.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.danp_lab2.HomeViewModel
import com.example.danp_lab2.data.Course
import com.example.danp_lab2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LisCourses(viewModel: HomeViewModel, navCotroller: NavHostController) {

    var listStudent = viewModel.getAllStudent().observeAsState().value
    var listCourse = viewModel.getAllCourse().observeAsState().value
    var listStudentCourseCrossRef = viewModel.getAllStudentCourseCrossRef().observeAsState().value

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        color = Color.White,
    ) {

        LazyColumn(modifier = Modifier.padding(10.dp)) {
            if (listStudent != null && listCourse != null && listStudentCourseCrossRef != null) {
                listCourse.forEach {
                    var aux = it
                    item {
                        Column() {
                            Text(text = it.courseName, color = Color.Black)
                            Column(
                                modifier = Modifier
                                    .background(color = colorResource(id = R.color.green_dark))
                                    .padding(20.dp)
                                    .fillMaxWidth()
                            ) {
                                listStudent.forEach {
                                    var aux2 = it
                                    listStudentCourseCrossRef.forEach {
                                        if (it.courseId == aux.courseId && it.studentId == aux2.studentId) {
                                            Text(text = aux2.studentName)
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                    }

                }
            }
        }
    }
}




