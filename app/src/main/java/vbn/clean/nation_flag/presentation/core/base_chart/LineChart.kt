package vbn.clean.nation_flag.presentation.core.base_chart

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.round

@Composable
fun LineChart(
    data: List<Pair<String, Double>> = emptyList(),
    modifier: Modifier = Modifier
) {
    val spacing = 100f
    val graphColor = colorResource(id = vbn.clean.nation_flag.R.color.line_chart)
    val line = colorResource(id = vbn.clean.nation_flag.R.color.line)
    val white = colorResource(id = vbn.clean.nation_flag.R.color.white)
    val transparentGraphColor = remember { graphColor.copy(alpha = 0.5f) }
    val upperValue = 100
    val lowerValue = 0
    val density = LocalDensity.current

    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.BLACK
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Canvas(modifier = modifier) {
        val spacePerHour = (size.width - spacing) / data.size
        (data.indices).forEach { i ->
            val hour = data[i].first
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    hour.toString(),
                    spacing + i * spacePerHour,
                    size.height,
                    textPaint
                )
            }
        }

        val priceStep = (upperValue - lowerValue) / 5f
        val textWidth = textPaint.measureText("118.0")
        (0..5).forEach { i ->
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    round(lowerValue + priceStep * i).toString(),
                    size.width - textWidth + 40,
                    size.height - spacing - i * size.height / 5f,
                    textPaint
                )
                drawLine(
                    color = line,
                    start = Offset(0f + 50, size.height - spacing - i * size.height / 5f),
                    end = Offset(size.width - textWidth - spacing/2, size.height - spacing - i * size.height / 5f),
                    strokeWidth = 1.dp.toPx()
                )
            }
        }

        val strokePath = Path().apply {
            val height = size.height
            data.indices.forEach { i ->
                val info = data[i]
                val ratio = (info.second - lowerValue) / (upperValue - lowerValue)

                val x1 = spacing + i * spacePerHour
                val y1 = height - spacing - (ratio * height).toFloat()

                if (i == 0) { moveTo(x1, y1) }
                lineTo(x1, y1)

                drawCircle(
                    color = graphColor,
                    radius = 20f,
                    center = Offset(x1, y1),
                )
            }
        }

        drawPath(
            path = strokePath,
            color = graphColor,
            style = Stroke(
                width = 2.dp.toPx(),
                cap = StrokeCap.Round
            )
        )

        val fillPath = android.graphics.Path(strokePath.asAndroidPath()).asComposePath().apply {
            lineTo(size.width - spacePerHour, size.height - spacing)
            lineTo(spacing, size.height - spacing)
            close()
        }

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    transparentGraphColor,
                    Color.Transparent
                ),
                endY = size.height - spacing
            )
        )

    }
}