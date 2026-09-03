package com.example.ca_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ca_1.ui.theme.CA1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CA1Theme {
                CA1()
            }
        }
    }
}

@Composable
fun CA1() {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    val transition = updateTransition(
        targetState = isExpanded,
        label = "Button Transition"
    )

    val width by transition.animateDp(
        transitionSpec = {
            tween(400)
        },
        label = "Width"
    ) { state ->
        if (state) 160.dp else 56.dp
    }

    val color by transition.animateColor(
        transitionSpec = {
            tween(400)
        },
        label = "Color"
    ) { state ->
        if (state)
            MaterialTheme.colorScheme.primary
        else
            MaterialTheme.colorScheme.secondary
    }

    val cornerRadius by transition.animateDp(
        transitionSpec = {
            tween(400)
        },
        label = "Corner Radius"
    ) { state ->
        if (state) 16.dp else 28.dp
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FloatingActionButton(
            onClick = {
                isExpanded = !isExpanded
            },
            modifier = Modifier.width(width),
            containerColor = color,
            shape = RoundedCornerShape(cornerRadius)
        ) {
            if (transition.currentState) {
                Row {
                    Text("Save Activity")
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Save Activity"
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Save Activity"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CA1Theme {
        CA1()
    }
}
