package com.example.customslider

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.customslider.SliderStyle.SliderStyle

@Composable
fun Slider(
    modifier: Modifier = Modifier,
    initialValue: Float?,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int,
    sliderStyle: SliderStyle,
    ) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {


        androidx.compose.material.Slider(value = , onValueChange = )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (sliderStyle.showRange){
                Text(text = valueRange.start.toInt().toString())
            }
            SliderMainPart(
                modifier = Modifier.weight(1f),
                initialValue = initialValue,
                valueRange = valueRange,
                steps = steps,
                sliderStyle = sliderStyle,

            )
            if (sliderStyle.showRange){
                Text(text = valueRange.endInclusive.toInt().toString())
            }
        }
    }
}

@Composable
fun SliderMainPart(
    modifier: Modifier = Modifier,
    initialValue: Float?,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int,
    sliderStyle: SliderStyle,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    var selectedValue by remember { mutableStateOf(initialValue ?: 0f) }
    var sliderSize by remember { mutableStateOf(IntSize.Zero) }
    var sliderWidth: Dp
    var sliderHeight: Dp

    Box(modifier = modifier) {
        BoxWithConstraints(modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { sliderSize = it }
        ) {
            val maxPx = constraints.maxWidth.toFloat()
            val widthDp: Dp
            with(LocalDensity.current) {
                widthDp = maxPx.toDp()
            }

            //UnselectedLine
            Box(modifier = Modifier.align(Alignment.Center)) {
                sliderStyle.unselectedLine()
            }
            //SelectedLine
            Box(modifier = Modifier.align(Alignment.Center)) {
                sliderStyle.selectedLine(selectedValue / valueRange.endInclusive*100)
            }

            //Dot
            //selectPlaceWhere DotShouldBe
            val endOfSelectedLine = widthDp * (selectedValue / valueRange.endInclusive)
            var sliderThumbSize by remember { mutableStateOf(IntSize.Zero) }
            val sliderThumbWidth: Dp
            with(LocalDensity.current) {
                sliderThumbWidth = sliderThumbSize.width.toDp()
            }
            val thumbPosition = endOfSelectedLine - sliderThumbWidth / 2

            Box(modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(
                    start = if (thumbPosition < 0.dp) {
                        0.dp
                    } else if (thumbPosition > widthDp) {
                        widthDp - sliderThumbWidth
                    } else {
                        thumbPosition
                    }
                )
                .onSizeChanged { sliderThumbSize = it }
            ) {
                sliderStyle.thumb()
            }
        }
        with(LocalDensity.current) {
            sliderHeight = sliderSize.height.toDp()
            sliderWidth = sliderSize.width.toDp()
        }

        BasicSliderSelector(
            modifier = Modifier
                .width(sliderWidth)
                .height(sliderHeight),
            selectValue = { selectedValue = it },
            selectedValue = selectedValue,
            valueRange = valueRange,
            steps = steps,
            interactionSource = interactionSource
        )
    }
}


