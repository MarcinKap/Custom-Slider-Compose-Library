package com.example.customslider

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
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
fun BasicSliderSelector(
    modifier: Modifier = Modifier,
    selectValue: (Float) -> Unit,
    selectedValue: Float,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
//    var sizeOfOnePart = 0f
    var sizeOfOnePart by remember { mutableStateOf(0f) }
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
                    top.linkTo(parent.top)
                    height = Dimension.fillToConstraints
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
                                changePosition(it.x, sizeOfOnePart, valueRange.endInclusive)
                            interactionSource.tryEmit(PressInteraction)
                        }
                    )
                }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        sliderPosition =
                            changePosition(change.position.x, sizeOfOnePart, valueRange.endInclusive)
                    }
                },
        ) { }
    }
}


fun changePosition(position: Float, sizeOfOnePart: Float, maxValues: Int): Int {
    val result = position / sizeOfOnePart
    return if (result > maxValues) maxValues else result.toInt()
}