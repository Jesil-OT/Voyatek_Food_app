package com.assessment.voyatek.features.add_food.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assessment.voyatek.R
import com.assessment.voyatek.features.theme.VoyatekTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodTopBar(
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        content = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = stringResource(R.string.add_new_food),
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 16.sp
                    )
                },

                navigationIcon = {
                    BackButton(
                        modifier = Modifier.padding(start = 16.dp),
                        onBackClick = onBackClick
                    )
                }
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFFE4E7EC)
            )
        }
    )

}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val gray =  Color(0xFFF0F2F5)
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(RoundedCornerShape(360.dp))
            .border(width = 1.dp, color = gray, shape = RoundedCornerShape(360.dp))
            .clickable { onBackClick() }
            .padding(10.dp),
        contentAlignment = Alignment.Center,
        content = {
            Icon(
                painter = painterResource(R.drawable.back_arrow),
                contentDescription = stringResource(R.string.back)
            )
        }
    )
}

@Preview
@Composable
private fun AddFoodTopBarPreview() {
    VoyatekTheme {
        AddFoodTopBar(
            onBackClick = {},
        )
    }
}