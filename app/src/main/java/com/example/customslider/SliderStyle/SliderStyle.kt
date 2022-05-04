package com.example.customslider.SliderStyle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Immutable
data class SliderStyle(
    val showUnselectedLine: Boolean = true,
    val unselectedLine: @Composable () -> Unit =
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray)
            )
        },
    val showSelectedLine: Boolean = true,
    val selectedLine: @Composable (percent: Float) -> Unit =
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent)
            ){
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxWidth(fraction = it/100)
                        .height(44.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
            }
        },
    val showProgressText: Boolean = true,
    val positionOfProgressText: ProgressTextPosition? = null,
    val showDots: Boolean = true,
    val showThumb: Boolean = true,
    val thumb: @Composable () -> Unit,
    val showRange: Boolean = true,
    val rangeTextStyle: TextStyle? = null,


    )