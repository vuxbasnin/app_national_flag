package vbn.clean.nation_flag.presentation.core.base_chart

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import vbn.clean.nation_flag.R
import vbn.clean.nation_flag.data.model.response.chart.BobbleChart

@Composable
fun BobbleChartAnimation(
    data: List<BobbleChart> = emptyList(),
    modifier: Modifier,
) {
    val bobbleColorMain = colorResource(id = R.color.color_bg_main)
    val infiniteTransition = rememberInfiniteTransition()

    Box(modifier = modifier.fillMaxSize()) {
        var canvasWidth by remember { mutableStateOf(0f) }
        var canvasHeight by remember { mutableStateOf(0f) }

        val xValue by infiniteTransition.animateFloat(
            initialValue = 100f,
            targetValue = 1300f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    3000,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        val yValue by infiniteTransition.animateFloat(
            initialValue = 100f,
            targetValue = 2500f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    3500,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        val xValue2 by infiniteTransition.animateFloat(
            initialValue = 1000f,
            targetValue = 1300f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    3000,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        val yValue2 by infiniteTransition.animateFloat(
            initialValue = 1000f,
            targetValue = 2500f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    3500,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        val xValue3 by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1300f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    3500,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        val yValue3 by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 2500f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    2500,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        Canvas(modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { layoutCoordinates ->
                canvasWidth = layoutCoordinates.size.width.toFloat()
                canvasHeight = layoutCoordinates.size.height.toFloat()
            }) {

            drawCircle(
                brush = Brush.linearGradient(
                    listOf(bobbleColorMain, bobbleColorMain),
                    start = Offset(xValue2 - 90, yValue2),
                    end = Offset(xValue2 + 90, yValue2)
                ),
                radius = 100f,
                center = Offset(xValue2, yValue2)
            )

            drawTextCentered(
                text = "ABB",
                center = Offset(xValue2, yValue2),
                textSize = 16.sp.toPx(),
                textColor = Color.Black
            )

            drawCircle(
                brush = Brush.linearGradient(
                    listOf(bobbleColorMain, bobbleColorMain),
                    start = Offset(xValue - 90, yValue),
                    end = Offset(xValue + 90, yValue)
                ),
                radius = 50f,
                center = Offset(xValue, yValue)
            )

            drawTextCentered(
                text = "VCB",
                center = Offset(xValue, yValue),
                textSize = 8.sp.toPx(),
                textColor = Color.Black
            )

            drawCircle(
                brush = Brush.linearGradient(
                    listOf(bobbleColorMain, bobbleColorMain),
                    start = Offset(xValue3 - 90, yValue3),
                    end = Offset(xValue3 + 90, yValue3)
                ),
                radius = 150f,
                center = Offset(xValue3, yValue3)
            )

            drawTextCentered(
                text = "CTG",
                center = Offset(xValue3, yValue3),
                textSize = 16.sp.toPx(),
                textColor = Color.Black
            )
        }
    }
}

fun DrawScope.drawTextCentered(
    text: String,
    center: Offset,
    textSize: Float,
    textColor: Color,
) {
    drawContext.canvas.nativeCanvas.apply {
        val paint = android.graphics.Paint().apply {
            color = textColor.toArgb()
            textAlign = android.graphics.Paint.Align.CENTER
            this.textSize = textSize
            isAntiAlias = true
        }
        drawText(
            text,
            center.x,
            center.y + textSize / 3, // Điều chỉnh vị trí y của văn bản để căn giữa
            paint
        )
    }
}