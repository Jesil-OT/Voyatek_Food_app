package com.assessment.voyatek.features.add_food.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assessment.voyatek.R
import com.assessment.voyatek.features.theme.VoyatekTheme

@Composable
fun ImageItemButton(
    modifier: Modifier = Modifier,
    text: String,
    image: Painter,
    contentDescription: String,
    onClick: () -> Unit,
) {
    val gray =  Color(0xFFF0F2F5)
    val fontColor = Color(0xFF1D2433)
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable { onClick() }
            .fillMaxWidth()
            .height(90.dp)
            .border(width = 1.dp, color = gray, shape = RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center,
        content = {
           Column(
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center,
               content = {
                   Icon(
                       modifier = Modifier.size(width = 36.dp, height = 33.dp),
                       painter = image,
                       contentDescription = contentDescription
                   )
                   Text(
                       text = text,
                       style = MaterialTheme.typography.labelSmall,
                       color = fontColor
                   )
               }
           )
        }
    )
}

@Preview
@Composable
private fun ImageItemButtonPreview() {
    VoyatekTheme {
        ImageItemButton(
            onClick = {},
            text = stringResource(R.string.take_photo),
            image = painterResource(R.drawable.camera_icon),
            contentDescription = stringResource(R.string.open_camara)
        )
    }
}