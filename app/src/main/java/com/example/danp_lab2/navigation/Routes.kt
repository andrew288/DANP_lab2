package com.example.danp_lab2.navigation

sealed class Routes(
    val route: String
) {
    object ScreenHome: Routes("HomeScreen")
}