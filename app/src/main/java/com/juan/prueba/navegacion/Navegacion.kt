package com.juan.prueba.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.juan.prueba.ui.login.LoginScreen
import com.juan.prueba.ui.login.LoginViewModel
import com.juan.prueba.ui.signup.RegistroScreen
import com.juan.prueba.ui.signup.RegistroViewModel


@Composable
fun Navegacion(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {

            val loginViewModel: LoginViewModel = LoginViewModel()
            LoginScreen(viewModel = loginViewModel, navController = navController)
        }
        composable("registro") {
            val registroViewModel: RegistroViewModel = RegistroViewModel()
            RegistroScreen(viewModel = registroViewModel, navController = navController)
        }
    }

}