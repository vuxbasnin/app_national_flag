package vbn.clean.nation_flag.presentation.core.base_chart

import android.graphics.Color.BLACK
import android.graphics.Color.GREEN
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vbn.clean.nation_flag.R
import vbn.clean.nation_flag.core.common.TagDatePattern
import vbn.clean.nation_flag.core.common.getDateOrTime
import vbn.clean.nation_flag.data.model.response.chart.CandlestickData
import kotlin.math.round

@Composable
fun CandleChart(listData: List<CandlestickData>, modifier: Modifier) {
    val spacing = 100f
    val lowerValue = listData.minOf { it.low }
    val upperValue = listData.maxOf { it.high }
    val colorUp = colorResource(id = R.color.color_up)
    val colorDown = colorResource(id = R.color.color_down)
    val line = colorResource(id = vbn.clean.nation_flag.R.color.line)
    val density = LocalDensity.current

    val textPaint = remember(density) {
        Paint().apply {
            color = BLACK
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Canvas(modifier = modifier) {
        val spacePerHour = (size.width - spacing) / listData.size
        (listData.indices).forEachIndexed { index, data ->
            if (index % 20 == 0) {
                val hour = listData[index].timestamp.getDateOrTime(
                    TagDatePattern.TIME_SERVER_5,
                    TagDatePattern.TIME_HOUR_MIN
                )
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        hour,
                        spacing + index * spacePerHour,
                        size.height,
                        textPaint
                    )
                }
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
                    end = Offset(
                        size.width - textWidth - spacing / 2,
                        size.height - spacing - i * size.height / 5f
                    ),
                    strokeWidth = 1.dp.toPx()
                )
            }
        }
        drawCandlesticks(listData, colorUp, colorDown, textWidth)
    }
}

fun DrawScope.drawCandlesticks(
    candlesticks: List<CandlestickData>,
    colorUp: Color,
    colorDown: Color,
    textWidth: Float,
) {
    val maxPrice = candlesticks.maxOf { it.high }
    val minPrice = candlesticks.minOf { it.low }
    val priceRange = maxPrice - minPrice
    val sizeMargin = size.height / 10

    val candleWidth = (size.width - textWidth - textWidth / 3) / candlesticks.size
    val numberCandle = (size.width - textWidth - textWidth / 3) / candleWidth

    candlesticks.forEachIndexed { index, candlestick ->
        val openY =
            size.height - size.height * (candlestick.open - minPrice) / priceRange - sizeMargin
        val closeY =
            size.height - size.height * (candlestick.close - minPrice) / priceRange - sizeMargin
        val highY =
            size.height - size.height * (candlestick.high - minPrice) / priceRange - sizeMargin
        val lowY =
            size.height - size.height * (candlestick.low - minPrice) / priceRange - sizeMargin

        val color = if (candlestick.close > candlestick.open) colorUp else colorDown

        // Draw high-low line
        drawLine(
            color = if (candlestick.close > candlestick.open) colorUp else colorDown,
            start = Offset(
                x = index * candleWidth + candleWidth / 2,
                y = highY
            ),
            end = Offset(
                x = index * candleWidth + candleWidth / 2,
                y = lowY
            ),
            strokeWidth = 4f,
        )

        // Draw open-close rectangle
        drawRect(
            color = color,
            topLeft = Offset(
                x = index * candleWidth,
                y = if (openY > closeY) closeY else openY
            ),
            size = androidx.compose.ui.geometry.Size(
                width = candleWidth,
                height = kotlin.math.abs(closeY - openY)
            )
        )
    }
}