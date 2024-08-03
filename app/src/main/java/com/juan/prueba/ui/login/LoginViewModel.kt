package com.juan.prueba.ui.login

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juan.prueba.data.model.User

import com.juan.prueba.data.repository.UserRepository
import com.juan.prueba.utils.validarEmail
import com.juan.prueba.utils.validarNumero
import com.juan.prueba.utils.validarPassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LoginViewModel() : ViewModel() {

    private val  _email = MutableLiveData<String>("")
    val email:LiveData<String> = _email
    private val  _password = MutableLiveData<String>("")
    val password:LiveData<String> = _password
    private val _errorEmail =   MutableLiveData<Boolean>(false)
    val errorEmail: LiveData<Boolean> = _errorEmail
    private val _errorPass =   MutableLiveData<Boolean>(false)
    val errorPass: LiveData<Boolean> = _errorPass

    private val _recordar =   MutableLiveData<Boolean>(false)
    val recordar: LiveData<Boolean> = _recordar
    private val _login =   MutableLiveData<Boolean>(false)
    val login: LiveData<Boolean> = _login
    fun onLoginChange(email:String,password:String){
        _email.value=email
        _password.value=password

    }
    fun onLoginCLick(){
        val currentEmail = email.value.orEmpty()
        val currentPassword = password.value.orEmpty()

        validateEmail(currentEmail)
        validatePassword(currentPassword)
//        val isEmailValid = errorEmail.value ?: false
//        val isPasswordValid = errorPass.value ?: false
//        if (!isPasswordValid && !isEmailValid) {
//            _login.value = true
//        }

        _login.value = true
    }
    private  fun validateEmail(email: String){
      _errorEmail.value=!validarEmail(email)
    }

    private fun validatePassword(password: String){
        _errorPass.value=!validarPassword(password)
    }



    fun recordar(){
        val currentRecodar= recordar.value ?: false
        _recordar.value=!currentRecodar
    }
    fun resetLogin(){
        _login.value=false
//        _errorEmail.value=false
//        _errorPass.value=false
//        _email.value=""
//        _password.value=""
//        _recordar.value=false
    }
}
