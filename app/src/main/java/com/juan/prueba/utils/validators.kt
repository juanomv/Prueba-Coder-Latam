package com.juan.prueba.utils

import android.util.Patterns

fun validarEmail(email:String):Boolean{
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validarPassword(password:String):Boolean{
    return Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!#%*?&])[A-Za-z\\d@$!#%*?&]{8,}\$").matches(password)
}

fun validarNumero(number: String):Boolean{
    return Regex("^\\+591 \\d{8}$").matches(number)
}

fun validarPasswordConfirmacion(password: String,passwordConfirmacion: String):Boolean{
    return password == passwordConfirmacion
}