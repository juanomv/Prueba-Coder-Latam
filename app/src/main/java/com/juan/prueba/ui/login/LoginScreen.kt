package com.juan.prueba.ui.login

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.sharp.Face
import androidx.compose.material.icons.sharp.Lock
import androidx.compose.material.icons.sharp.Password
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.juan.prueba.R
import com.juan.prueba.ui.componetes.ButtonAccion
import com.juan.prueba.ui.componetes.Cabecera
import com.juan.prueba.ui.componetes.InputField
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val errorEmail: Boolean by viewModel.errorEmail.observeAsState(initial = false)
    val errorPass: Boolean by viewModel.errorPass.observeAsState(initial = false)
    val isLogin: Boolean by viewModel.login.observeAsState(initial = false)
    val isRecordar: Boolean by viewModel.recordar.observeAsState(initial = false)
    val colorPrimario = Color(0xFF28208F)

    var olvideMiContrasena by remember { mutableStateOf(false) }


    val snackbarHostState = remember { SnackbarHostState() }



    LaunchedEffect(isLogin,viewModel,olvideMiContrasena) {

        if (isLogin) {
            snackbarHostState.showSnackbar("Se presionó el botón ingresar")
            viewModel.resetLogin()
        }
        if (olvideMiContrasena) {
            snackbarHostState.showSnackbar("Envía a resetear la Contraseña.")
            olvideMiContrasena=false
        }

    }

    LaunchedEffect(isRecordar) {
        if (isRecordar) {
            snackbarHostState.showSnackbar("Se activó recordar.")
        }
        if(!isRecordar){
            snackbarHostState.showSnackbar("Se desactivó recordar.")
        }
    }



    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },

    ) { contentPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
        ) {

                Cabecera(
                    modifier = Modifier.fillMaxWidth(),
                    imageHeight = 330.dp,
                    curveHeight = 200.dp,
                    text = "Ingreso",
                    colorPrimario = colorPrimario,
                    imageResourceId = R.drawable.logo
                )

            // Formulario
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .offset(y = 280.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(25.dp))

                InputField(
                    label = "Correo electrónico",
                    value = email,
                    onValueChange = { viewModel.onLoginChange(it, password) },
                    leadingIcon = Icons.Filled.Email,
                    isError = errorEmail,
                    error = "Correo Invalido",
                    placeHolder = "Ingresar correo electrónico"
                )


                Spacer(modifier = Modifier.height(16.dp))

                InputField(
                    label = "Contraseña",
                    value = password,
                    onValueChange = { viewModel.onLoginChange(email, it) },
                    leadingIcon = Icons.Sharp.Password,
                    isPassword = true,
                    isError = errorPass,
                    error = "Contraseña incorrecta",
                    placeHolder = "Ej: abcABC#123"

                )


                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = isRecordar,
                            onCheckedChange = {
                                viewModel.recordar()
                            }
                        )
                        Text(text = "Recordarme")
                    }
                    Text(
                        text = "Contraseña olvidada?",
                        modifier = Modifier.clickable {
                           olvideMiContrasena = true
                        },
                        style = TextStyle(
                            color = colorPrimario
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                ButtonAccion(
                    onClick = {viewModel.onLoginCLick()},
                    text = "Ingresar"
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "No tienes una cuenta? ", style = TextStyle(color = Color.Black))
                    Text(
                        text = "Registrarse",
                        style = TextStyle(color = colorPrimario),
                        modifier = Modifier.clickable {
                            navController.navigate("registro")
                        }
                    )
                }
            }
        }
    }
}



