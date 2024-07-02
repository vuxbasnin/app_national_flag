package vbn.clean.nation_flag.presentation.core.base_chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vbn.clean.nation_flag.R
import vbn.clean.nation_flag.data.model.response.chart.BobbleChart
import kotlin.random.Random

@Composable
fun BobbleChartVolume(
    data: List<BobbleChart> = emptyList(),
    modifier: Modifier,
) {
    val colorRadiusMain = colorResource(id = R.color.color_radius_main)
    val colorBgMain = colorResource(id = R.color.color_bg_main)
    val colorRadiusDefault = colorResource(id = R.color.color_radius_default)
    val colorBgDefault = colorResource(id = R.color.color_bg_default)
    val colorLine = colorResource(id = R.color.line)
    var circleRadius = 10f

    Box(modifier = modifier) {
        var canvasWidth by remember { mutableStateOf(0f) }
        var canvasHeight by remember { mutableStateOf(0f) }

        // Tạo danh sách các vị trí ngẫu nhiên
        val positions = remember { mutableStateListOf<Offset>() }

        LaunchedEffect(Unit) {
            if (canvasWidth > 0 && canvasHeight > 0) {
                val newPositions = mutableListOf<Offset>()
                var currentY = data.getOrNull(0)?.value?.div(2)
                if (currentY != null) {
                    for (bobble in data) {
                        val radius = bobble.value ?: 0f
                        val newPosition = Offset(
                            x = Random.nextFloat() * (canvasWidth - 2 * radius + radius),
                            y = currentY + 50
                        )
                        newPositions.add(newPosition)
                        currentY += radius * 1.2f
                    }
                    positions.addAll(newPositions)
                }
            }
        }

        Canvas(modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { layoutCoordinates ->
                canvasWidth = layoutCoordinates.size.width.toFloat()
                canvasHeight = layoutCoordinates.size.height.toFloat()
            }) {
            var heightLine = 0f
            var widthLine = 0f
            (0..5).forEachIndexed { index, pos ->
                drawLine(
                    color = colorLine,
                    strokeWidth = 1.dp.toPx(),
                    start = Offset(0f, heightLine),
                    end = Offset(canvasWidth, heightLine)
                )
                heightLine += canvasHeight / 6
            }

            val dashPathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

            (0..4).forEachIndexed { index, pos ->
                drawLine(
                    color = colorLine,
                    strokeWidth = 1.dp.toPx(),
                    start = Offset(widthLine, 0f),
                    end = Offset(widthLine, canvasHeight),
                    pathEffect = dashPathEffect
                )
                widthLine += canvasWidth / 4
            }

            positions.forEachIndexed { index, position ->
                val bobble = data[index]
                circleRadius = (bobble.value ?: 0f) / 1.5f
                val borderWidth = 2.dp.toPx()

                //draw radius
                drawCircle(
                    color = if (bobble.isMain == true) colorRadiusMain else colorRadiusDefault,
                    radius = circleRadius - borderWidth,
                    center = position
                )

                //draw bg
                drawCircle(
                    color = if (bobble.isMain == true) colorBgMain else colorBgDefault,
                    radius = circleRadius - borderWidth / 2,
                    center = position,
                    style = Stroke(width = borderWidth),
                    alpha = 0.5f
                )

                drawContext.canvas.nativeCanvas.apply {
                    val text = bobble.name ?: ""
                    val textSize = circleRadius.sp.toPx() / 5    //  radius/5
                    val paint = android.graphics.Paint().apply {
                        color =
                            if (bobble.isMain == true) android.graphics.Color.parseColor("#FFB62A") else android.graphics.Color.BLACK
                        textAlign = android.graphics.Paint.Align.CENTER
                        this.textSize = textSize
                        isAntiAlias = true
                        typeface = android.graphics.Typeface.create(
                            android.graphics.Typeface.DEFAULT,
                            android.graphics.Typeface.BOLD
                        )
                    }
                    drawText(
                        text,
                        position.x,
                        position.y + textSize / 4,
                        paint
                    )
                }
            }
        }
    }
}