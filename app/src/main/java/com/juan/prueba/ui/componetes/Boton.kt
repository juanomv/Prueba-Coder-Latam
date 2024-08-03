package com.juan.prueba.ui.componetes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

@Composable
fun ButtonAccion(
    onClick: () -> Unit,
    text: String,
    colorPrimario: Color = Color(0xFF28208F) ){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorPrimario
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text)
    }
}