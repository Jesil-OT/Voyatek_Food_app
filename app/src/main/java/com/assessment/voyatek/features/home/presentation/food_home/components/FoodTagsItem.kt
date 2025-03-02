package com.assessment.voyatek.features.home.presentation.food_home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FoodTagsItem(
    modifier: Modifier = Modifier,
    tag: String,
) {
    val textColor = Color(0xFF645D5D)
    val backgroundColor = Color(0xFFFBF1F1)

    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(16.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center,
        content = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                text = tag,
                color = textColor,
                fontSize = 12.sp,
                style = MaterialTheme.typography.labelSmall
            )
        }
    )
}