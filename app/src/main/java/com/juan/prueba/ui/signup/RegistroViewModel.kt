package com.juan.prueba.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juan.prueba.utils.validarEmail
import com.juan.prueba.utils.validarNumero
import com.juan.prueba.utils.validarPassword
import com.juan.prueba.utils.validarPasswordConfirmacion

class RegistroViewModel():ViewModel() {
    private val  _email = MutableLiveData<String>("")
    val email: LiveData<String> = _email
    private val  _movil = MutableLiveData<String>("")
    val movil: LiveData<String> = _movil
    private val  _password = MutableLiveData<String>("")
    val password: LiveData<String> = _password
    private val  _cofirmPassword = MutableLiveData<String>("")
    val cofirmPassword: LiveData<String> = _cofirmPassword
    private val _errorEmail =   MutableLiveData<Boolean>(false)
    val errorEmail: LiveData<Boolean> = _errorEmail
    private val _errorNumber =   MutableLiveData<Boolean>(false)
    val errorNumber: LiveData<Boolean> = _errorNumber
    private val _errorPass =   MutableLiveData<Boolean>(false)
    val errorPass: LiveData<Boolean> = _errorPass

    private val _errorPassConfirmation =   MutableLiveData<Boolean>(false)
    val errorPassConfirmation: LiveData<Boolean> = _errorPassConfirmation

    private val _registar =   MutableLiveData<Boolean>(false)
    val registar: LiveData<Boolean> = _registar

    fun onRegistroChange(email:String,movil:String,password:String,confirmacionPassworr:String){
        _email.value=email
        _movil.value=movil
        _password.value=password
        _cofirmPassword.value=confirmacionPassworr
    }



    private  fun validateEmail(email: String){
        _errorEmail.value=!validarEmail(email)
    }

    private fun validateNumber(number: String){
        _errorNumber.value=!validarNumero(number)
    }

    private fun validatePassword(password: String){
        _errorPass.value=!validarPassword(password)
    }
    private fun validatePasswordConfimacion(password: String, passwordConfirmatio: String){


        _errorPassConfirmation.value=!validarPasswordConfirmacion(password,passwordConfirmatio)

    }


    fun onRegistroCLick(){
        val currentEmail = email.value.orEmpty()
        val currentNumber = movil.value.orEmpty()
        val currentPassword = password.value.orEmpty()
        val currentPasswordConfir = cofirmPassword.value.orEmpty()

        validateEmail(currentEmail)
        validateNumber(currentNumber)
        validatePassword(currentPassword)
        validatePasswordConfimacion(currentPassword,currentPasswordConfir)
//        val isEmailValid = errorEmail.value ?: false
//        val isNumberValid = errorNumber.value ?: false
//        val isPasswordValid = errorPass.value ?: false
//        val isPasswordConfirValid = errorPassConfirmation.value ?: false
//        if (!isPasswordValid && !isEmailValid && !isNumberValid && !isPasswordConfirValid) {
//            _registar.value = true
//        }

        _registar.value = true
    }

    fun resetResgitro(){
        _registar.value=false
//        _errorEmail.value=false
//        _errorNumber.value=false
//        _errorPassConfirmation.value=false
//        _errorPass.value=false
//        _email.value=""
//        _movil.value=""
//        _password.value=""
//        _cofirmPassword.value=""

    }
}