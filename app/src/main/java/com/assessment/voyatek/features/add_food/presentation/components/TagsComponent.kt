package com.assessment.voyatek.features.add_food.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.assessment.voyatek.features.add_food.presentation.model.TagUI

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsComponent(
    modifier: Modifier = Modifier,
    tags: List<TagUI>,
    onDeleteTag: (TagUI) -> Unit
) {
    val gray = Color(0xFF98A2B3)
    Box(
        modifier = modifier.fillMaxWidth(),
        content = {
            if (tags.isEmpty()) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 15.dp),
                    text = stringResource(R.string.add_a_tag),
                    style = MaterialTheme.typography.labelSmall,
                    color = gray
                )
            } else {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    overflow = FlowRowOverflow.Clip
                ) {
                    tags.forEach { tag ->
                        TagComponent(
                            tag = tag.name,
                            onDeleteTag = { onDeleteTag(tag) }
                        )
                    }
                }
            }
        }
    )

}

@Composable
fun TagComponent(
    modifier: Modifier = Modifier,
    tag: String,
    onDeleteTag: (String) -> Unit
) {
    val backgroundGray = Color(0xFFF0F2F5)
    val textColor = Color(0xFF1D2433)
    Row(
        modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color = backgroundGray)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Text(
                text = tag,
                color = textColor,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 10.sp
            )
            Icon(
                modifier = Modifier.clickable { onDeleteTag(tag) },
                painter = painterResource(R.drawable.tag_remove),
                contentDescription = stringResource(R.string.remove_tag),
            )
        }
    )
}

@Preview
@Composable
private fun TagsComponentPreview() {
    TagsComponent(
        tags = listOf(),
        onDeleteTag = {}
    )
}

@Preview
@Composable
private fun TagComponentPreview() {
    TagComponent(
        tag = tags[0],
        onDeleteTag = {}
    )
}

// for testing purposes
internal val tags = listOf(
    "healthy",
    "Fibre",
    "vegetarian",
    "Spicy",
    "Keto",
    "Low carb",
    "Creamy"
)