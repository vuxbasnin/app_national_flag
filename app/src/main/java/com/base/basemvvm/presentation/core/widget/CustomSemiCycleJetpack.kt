package com.base.basemvvm.presentation.core.widget

import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.google.android.material.math.MathUtils.lerp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.math.cos
import kotlin.math.sin

private val INDICATOR_LENGTH = 14.dp
private val MAJOR_INDICATOR_LENGTH = 18.dp
private val INDICATOR_INITIAL_OFFSET = 5.dp

@Composable
fun SpeedometerScreen(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    Column(modifier = modifier) {
        val speed = remember { Animatable(0f) }

        Speedometer(
            currentSpeed = speed.value,
            modifier = Modifier
                .padding(180.dp)
                .requiredSize(180.dp)
        )

        Slider(
            value = speed.value,
            valueRange = 0f..180f,
            onValueChange = {
                coroutineScope.launch {
                    speed.animateTo(
                        it,
                        animationSpec = tween(durationMillis = 50, easing = LinearOutSlowInEasing)
                    )
                }
            },
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Composable
private fun Speedometer(
    @FloatRange(from = 0.0, to = 180.0) currentSpeed: Float,
    modifier: Modifier = Modifier
) {
    val textMeasurer = rememberTextMeasurer()
    val textColor = MaterialTheme.colorScheme.onSurface
    val indicatorColor = MaterialTheme.colorScheme.onSurface
    Canvas(modifier = modifier, onDraw = {
        drawArc(
            color = Color.Red,
            startAngle = 0f,
            sweepAngle = -180f,
            useCenter = false,
            style = Stroke(width = 10.0.dp.toPx())
        )

        for (angle in 270 downTo 90 step 2) {
            val speed = 270 - angle
            if (speed % 45 == 0 || speed % 45 == 1) {
                speedText(
                    name = name(speed),
                    angle = angle,
                    textMeasurer = textMeasurer,
                    textColor = textColor
                )
            }
        }

        Timber.d("NINVB => current speed $currentSpeed")
        speedIndicator(speedAngle = 270 - currentSpeed)
    })
}

private fun name(speed: Int): String {
    return when (speed) {
        0 -> {
            "Bán mạnh"
        }

        46 -> {
            "Bán"
        }

        90 -> {
            "Theo dõi"
        }

        136 -> {
            "Mua"
        }

        180 -> {
            "Mua mạnh"
        }

        else -> {
            ""
        }
    }
}

private fun DrawScope.speedText(
    name: String,
    angle: Int,
    textColor: Color,
    textMeasurer: TextMeasurer
) {
    val textLayoutResult = textMeasurer.measure(
        text = name,
        style = TextStyle.Default.copy(lineHeight = TextUnit(0.0f, TextUnitType.Sp))
    )
    val textWidth = textLayoutResult.size.width
    val textHeight = textLayoutResult.size.height

    val textOffset = pointOnCircle(
        thetaInDegrees = angle.toDouble(),
        radius = size.height / 2 - MAJOR_INDICATOR_LENGTH.toPx() - textWidth / 2 - INDICATOR_INITIAL_OFFSET.toPx(), // Adjusting radius with text width
        cX = center.x,
        cY = center.y
    )

    drawContext.canvas.save()
    // Translate to the text offset point, adjusting for vertical centering.
    drawContext.canvas.translate(
        textOffset.x - textWidth / 2,
        textOffset.y - textHeight / 2
    )

    drawText(textLayoutResult, color = textColor)

    drawContext.canvas.restore()
}

private fun DrawScope.speedIndicator(
    speedAngle: Float
) {
    val endOffset = pointOnCircle(
        thetaInDegrees = speedAngle.toDouble(),
        radius = size.height / 2 - INDICATOR_LENGTH.toPx(),
        cX = center.x,
        cY = center.y,
        isIndicator = true
    )

    for (i in 0 until 100) {
        val ratio = i.toFloat() / 100.toFloat()
        val currentThickness = lerp(12f, 4f, ratio)
        val currentPoint = Offset(
            center.x + (endOffset.x - center.x) * ratio,
            center.y + (endOffset.y - center.y) * ratio
        )

        this.drawLine(
            color = Color.Magenta,
            start = currentPoint,
            end = currentPoint,
            strokeWidth = currentThickness,
            cap = StrokeCap.Round
        )
    }
}

private fun pointOnCircle(
    thetaInDegrees: Double,
    radius: Float,
    cX: Float = 0f,
    cY: Float = 0f,
    isIndicator: Boolean = false
): Offset {
    val x = cX + (radius * sin(Math.toRadians(thetaInDegrees)).toFloat())
    val y = cY + (radius * cos(Math.toRadians(thetaInDegrees)).toFloat())
    return Offset(x, y)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SpeedometerPreview() {
    SpeedometerScreen()
}