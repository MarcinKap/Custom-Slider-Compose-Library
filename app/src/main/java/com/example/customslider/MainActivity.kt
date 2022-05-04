package com.example.customslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.customslider.SliderStyle.SliderStyle
import com.example.customslider.ui.theme.CustomSliderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomSliderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        Box(modifier = Modifier.padding(16.dp)) {
                            Card(Modifier) {
                                NumericalSliderSelector(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp),
                                    selectValue = {},
                                    selectedValue = 1,
                                    maxValues = 10f,
                                    color = Color(0xff0082d5)
                                )
                            }
                        }
                        Box(modifier = Modifier.padding(16.dp)) {
                            Card(Modifier) {
                                CustomSliderSelector(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp),
                                    selectValue = {},
                                    selectedValue = 1,
                                    listValues = listOf(
                                        SliderParameter("Low", 1),
                                        SliderParameter("Medium", 2),
                                        SliderParameter("High", 3),
                                    ),
                                    color = Color(0xff0082d5)
                                )
                            }
                        }
                        Box(modifier = Modifier.padding(16.dp)) {
                            Card(Modifier.fillMaxWidth()) {
                                Slider(
                                    modifier = Modifier.wrapContentSize().padding(10.dp).fillMaxWidth(),
                                    initialValue = 10f,
                                    valueRange = 0f..100f,
                                    steps = 100,
                                    sliderStyle = SliderStyle(
                                        thumb = {
                                            Box(
                                                modifier = Modifier
                                                    .size(5.dp)
                                                    .background(Color.Green)
                                            )
                                        }
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomSliderTheme {
        Greeting("Android")
    }
}