package com.example.danp_lab2.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.danp_lab2.HomeViewModel
import com.example.danp_lab2.R
import com.example.danp_lab2.components.DropDownCursos
import com.example.danp_lab2.data.Student
import com.example.danp_lab2.data.relations.StudentCourseCrossRef


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentRegister(viewModel: HomeViewModel, navCotroller: NavHostController){

    var studentNames by rememberSaveable { mutableStateOf(("")) }
    var studentCod by rememberSaveable { mutableStateOf(("")) }
    //var courseId by rememberSaveable { mutableStateOf(("")) }
    var courseId = remember { mutableStateOf(0) }
    var courseName = remember { mutableStateOf("") }
    //var listStudent = viewModel.getAllStudent().observeAsState().value
    var listCourse = viewModel.getAllCourse().observeAsState().value

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        color = Color.White,
    ) {

        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), verticalArrangement = Arrangement.Center ) {

            item {
                Text(text = "Registrar Estudiante", textAlign = TextAlign.Center,
                    modifier = Modifier.width(150.dp),fontSize = 30.sp,fontWeight = FontWeight.ExtraBold, color = Color.Black)

                TextField(
                    modifier = Modifier.padding(top = 10.dp),
                    value = studentNames,
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.baseline_person_24), contentDescription = null) },
                    onValueChange = {
                        studentNames = it

                    } ,
                    label = { Text(text = "Nombres y apellidos") },
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.White, textColor = Color.Black)
                )
                TextField(
                    modifier = Modifier.padding(top = 10.dp),
                    value = studentCod,
                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.baseline_vpn_key_24), contentDescription = null) },
                    onValueChange = {
                        studentCod = it

                    } ,
                    label = { Text(text = "Codigo estudiante") },
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.White, textColor = Color.Black),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

                )
                // pruebas
                if(listCourse !=null) {
                    DropDownCursos(categories = listCourse, selectedCategory = courseName, selectedCategoryId = courseId )
                }

                Row(
                    modifier = Modifier.padding(top = 30.dp)
                ) {
                    // Boton volver
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                        modifier = Modifier.padding(end = 15.dp),
                        onClick = {
                            navCotroller.navigate("home")
                        }) {
                        Text(text = "Cancelar")
                    }
                    // boton create
                    Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Green), onClick = {
                        viewModel.addStudent(Student(studentCod.toInt(), studentNames,studentCod))
                        viewModel.addStudentCourseCrossRef(StudentCourseCrossRef(studentCod.toInt(), courseId.value))
                    }) {
                        Text(text = "Matricular")
                    }
                }
            }
        }
    }
}
