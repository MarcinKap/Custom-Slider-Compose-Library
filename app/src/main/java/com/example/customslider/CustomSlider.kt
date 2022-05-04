package com.example.customslider

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension


@Composable
fun CustomSliderSelector(
    modifier: Modifier = Modifier,
    selectValue: (Any) -> Unit,
    selectedValue: Any,
    listValues: List<SliderParameter>,
    color: Color
) {
    val steps = listValues.size-1
    var sizeOfOnePart = 0f
    var sliderPosition by remember { mutableStateOf(selectedValue) }

    selectValue(sliderPosition)



    ConstraintLayout(
        modifier = modifier
    ) {
        val (painRow) = createRefs()
        Row(
            modifier = Modifier
                .constrainAs(painRow) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .onGloballyPositioned { coordinates ->
                    sizeOfOnePart =
                        (coordinates.size.width.toFloat() / (steps + 1))
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            sliderPosition =
                                changePositionCustomSlider(it.x, sizeOfOnePart, listValues)
                        }
                    )
                }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        sliderPosition =
                            changePositionCustomSlider(change.position.x, sizeOfOnePart, listValues)
                    }
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            for (index in 0..steps) {
                val difference = listValues.indexOf(sliderPosition) - index.toFloat()
                val boolean = difference >= -0.5f && difference < 0.5f
                var isSelected by remember { mutableStateOf(boolean) }
                isSelected = boolean
                val animatedTextColor = animateColorAsState(
                    if (!isSelected) Color.Gray else Color.DarkGray
                )
                val color = color
//                    colorByPain(
//                    selectedPain = index,
//                    maxValues = maxValues,
//                    startColor = colorResource(id = R.color.purple_200),
//                    endColor = colorResource(id = R.color.teal_200)
//                )
                val animatedBoxColor = animateColorAsState(
                    if (!isSelected) color.copy(0.9f) else color
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = LinearOutSlowInEasing
                                )
                            ),
                        text = listValues[index].seenValue,
                        color = animatedTextColor.value,
                        textAlign = TextAlign.Center,
                        fontSize = if (isSelected) 24.sp else 14.sp
                    )
                    Box(
                        modifier = Modifier
                            .animateContentSize(
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = LinearOutSlowInEasing
                                )
                            )
                            .padding(vertical = 4.dp)
                            .background(color = animatedBoxColor.value)
                            .width(if (isSelected) 6.dp else 5.dp)
                            .height(if (isSelected) 60.dp else 30.dp)
                    )
                }
            }
        }
    }


}


fun changePositionCustomSlider(position: Float, sizeOfOnePart: Float, listValues: List<SliderParameter>): SliderParameter {
    val result = position / sizeOfOnePart
    val item = if (result > listValues.size) listValues.size else result
    return listValues[item.toInt()]
}

//@Composable
//fun colorByPain(selectedPain: Int, maxValues: Float, startColor: Color, endColor: Color): Color {
//
//
//
//}