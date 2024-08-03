package com.juan.prueba.ui.signup


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.sharp.Call
import androidx.compose.material.icons.sharp.Password
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.juan.prueba.R
import com.juan.prueba.ui.componetes.ButtonAccion
import com.juan.prueba.ui.componetes.Cabecera
import com.juan.prueba.ui.componetes.InputField

@Composable
fun RegistroScreen(viewModel: RegistroViewModel, navController: NavController) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val movil: String by viewModel.movil.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val confirmarPassword: String by viewModel.cofirmPassword.observeAsState(initial = "")
    val errorEmail: Boolean by viewModel.errorEmail.observeAsState(initial = false)
    val errorNumber: Boolean by viewModel.errorNumber.observeAsState(initial = false)
    val errorPass: Boolean by viewModel.errorPass.observeAsState(initial = false)
    val errorPassConfirmation: Boolean by viewModel.errorPassConfirmation.observeAsState(initial = false)
    val isRegistro: Boolean by viewModel.registar.observeAsState(initial = false)
    val colorPrimario = Color(0xFF28208F)
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(isRegistro,viewModel) {
    if (isRegistro) {
        snackbarHostState.showSnackbar("Se presionó el botón crear cuenta")
        viewModel.resetResgitro()
    }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },

        content = { innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {

                Cabecera(
                    modifier = Modifier.fillMaxWidth(),
                    imageHeight = 250.dp,
                    curveHeight = 200.dp,
                    text = "Registro",
                    colorPrimario = colorPrimario,
                    imageResourceId = R.drawable.logo
                )

                // Form
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .offset(y = 200.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    InputField(
                        label = "Correo electrónico",
                        value = email,
                        onValueChange = { viewModel.onRegistroChange(it, movil, password, confirmarPassword) },
                        leadingIcon = Icons.Filled.Email,
                        isError = errorEmail,
                        error = "Correo Invalido",
                        placeHolder = "Ingrese correo electrónico"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    InputField(
                        label = "Número de teléfono",
                        value = movil,
                        onValueChange = { viewModel.onRegistroChange(email, it, password, confirmarPassword) },
                        leadingIcon = Icons.Sharp.Call,
                        isError = errorNumber,
                        error = "Número invalido",
                        placeHolder = "Ej: +591 12345678"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    InputField(
                        label = "Contraseña",
                        value = password,
                        onValueChange = { viewModel.onRegistroChange(email, movil, it, confirmarPassword) },
                        leadingIcon = Icons.Sharp.Password,
                        isPassword = true,
                        isError = errorPass,
                        error = "Contraseña Debil",
                        placeHolder = "Ej: abcABC#123"

                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    InputField(
                        label = "Confirmar contraseña",
                        value = confirmarPassword,
                        onValueChange = { viewModel.onRegistroChange(email, movil, password, it) },
                        leadingIcon = Icons.Sharp.Password,
                        isPassword = true,
                        isError = errorPassConfirmation,
                        error = "La contraseña no coincide",
                        placeHolder = "Reingresar contraseña"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ButtonAccion(
                        onClick = {viewModel.onRegistroCLick()},
                        text = "Registro"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Ya tienes una cuenta? ", style = TextStyle(color = Color.Black))
                        Text(
                            text = "Ingresar",
                            style = TextStyle(color = colorPrimario),
                            modifier = Modifier.clickable { navController.navigate("login") }
                        )
                    }
                }
            }
        }
    )
}

