package com.assessment.voyatek.features.home.presentation.food_home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assessment.voyatek.R
import com.assessment.voyatek.features.theme.VoyatekTheme

@Composable
fun HomeSearch(
    modifier: Modifier = Modifier
) {
    var search by remember { mutableStateOf("") }
    val backgroundColor = Color(0xFFF7F9FC)
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(color = backgroundColor),
        value = search,
        onValueChange = { search = it },
        textStyle = MaterialTheme.typography.labelSmall,
        placeholder = {
            Text(
                text = stringResource(R.string.search_foods),
                style = MaterialTheme.typography.labelSmall
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.home_search),
                contentDescription = stringResource(R.string.search_foods)
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent,
        )
    )
}

@Preview
@Composable
private fun HomeSearchPreview() {
    VoyatekTheme {
        HomeSearch()
    }
}