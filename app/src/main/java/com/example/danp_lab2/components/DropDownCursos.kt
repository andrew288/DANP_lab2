package com.example.danp_lab2.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.danp_lab2.R
import com.example.danp_lab2.data.Course

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownCursos(
    categories: List<Course>,
    selectedCategory: MutableState<String>,
    selectedCategoryId: MutableState<Int>
) {
    var expanded = remember { mutableStateOf(false) }
    var textFieldSize = remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded.value) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    val customTextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Color.Black,
        containerColor = Color.White,
        focusedIndicatorColor = colorResource(id = R.color.green_light),
    )

    Column(modifier = Modifier.padding(start = 40.dp, end = 40.dp)) {
        OutlinedTextField(
            value = selectedCategory.value,
            onValueChange = { selectedCategory.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize.value = coordinates.size.toSize()
                },
            label = { Text(text = "Curso", color = colorResource(id = R.color.green_dark),) },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    Modifier.clickable { expanded.value = !expanded.value })
            },
            colors = customTextFieldColors,
            readOnly = true
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.value.width.toDp() })
        ) {
            categories.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedCategory.value = label.courseName
                    selectedCategoryId.value = label.courseId
                    expanded.value = false
                }, text = { Text(text = label.courseName) })
            }

        }

    }
}