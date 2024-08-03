package com.juan.prueba.ui.componetes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juan.prueba.R
@Composable
fun Cabecera(
    modifier: Modifier = Modifier,
    imageHeight:Dp = 250.dp,
    curveHeight:Dp = 200.dp,
    text: String = "Registro",
    colorPrimario: Color = Color(0xFF28208F),
    imageResourceId: Int = R.drawable.logo
) {
    Box(modifier = modifier.fillMaxWidth()) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = "Fondo con curva",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
                .offset(y = (-30).dp)
        )

        // Curvas
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(curveHeight)
            .align(Alignment.BottomCenter)
        ) {
            val width = size.width
            val height = size.height

            val path = Path().apply {
                moveTo(0f, height * 0.5f)
                quadraticTo(
                    width * 0.25f, height * 0.25f,
                    width * 0.5f, height * 0.5f
                )
                quadraticTo(
                    width * 0.75f, height * 0.75f,
                    width, height * 0.5f
                )
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            val path2 = Path().apply {
                moveTo(0f, height * 0.51f)
                quadraticTo(
                    width * 0.28f, height * 0.28f + 3.dp.toPx(),
                    width * 0.52f, height * 0.52f + 3.dp.toPx()
                )
                quadraticTo(
                    width * 0.75f, height * 0.75f + 5.dp.toPx(),
                    width, height * 0.5f + 5.dp.toPx()
                )
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            drawPath(path = path, color = Color(0x726B6A6A), style = Fill)
            drawPath(path = path2, color = Color.White, style = Fill)
        }

        // Texto con línea debajo
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(y = (-50).dp)
                .padding(horizontal = 16.dp)
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = text,
                    color = colorPrimario,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 24.sp),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Canvas(modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                ) {
                    drawLine(
                        color = colorPrimario,
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = size.width / 7.2f, y = 0f),
                        strokeWidth = size.height
                    )
                }
            }
        }
    }
}