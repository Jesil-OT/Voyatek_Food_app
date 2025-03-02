package com.assessment.voyatek.features.home.presentation.food_home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.assessment.voyatek.R
import com.assessment.voyatek.features.home.domain.Food
import com.assessment.voyatek.features.home.presentation.model.FoodUI
import com.assessment.voyatek.features.home.presentation.model.toFoodUI
import com.assessment.voyatek.features.theme.VoyatekTheme

@Composable
fun FoodListItem(
    modifier: Modifier = Modifier,
    foodUI: FoodUI,
    onClick: () -> Unit
) {
    val caloriesTextColor = Color(0xFFF707989)
    val strokeColor = Color(0xFFE4E7EC)
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(1.dp, strokeColor, RoundedCornerShape(4.dp))
            .clickable { onClick() },
        content = {
            Box(
                modifier = Modifier,
                content = {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth().height(137.dp),
                        contentScale = ContentScale.Crop,
                        model = foodUI.foodImage,
                        contentDescription = foodUI.foodName,
                    )
                }
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                content = {
                   Row(
                       modifier = Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.SpaceBetween,
                       verticalAlignment = Alignment.CenterVertically,
                       content = {
                           Text(
                               text = foodUI.foodName,
                               style = MaterialTheme.typography.titleLarge
                           )
                           Image(
                               imageVector = ImageVector.vectorResource(R.drawable.heart_unlike),
                               contentDescription = stringResource(R.string.add_to_favorite),
                               modifier = Modifier.wrapContentWidth()
                           )
                       }
                   )
                    Row(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.food_steak),
                                contentDescription = foodUI.foodName,
                                tint = Color(0xFFFD42620)
                            )
                            Text(
                                text = foodUI.foodCalories,
                                style = MaterialTheme.typography.labelSmall,
                                color = caloriesTextColor
                            )
                        }
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = foodUI.foodDescription,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 8.dp),
                        content = {
                            items(foodUI.foodTags){ foodTag ->
                                FoodTagsItem(tag = foodTag)
                            }
                        }
                    )
                }
            )
        }
    )

}

@Preview
@Composable
private fun FoodListItemPreview() {
    VoyatekTheme {
        FoodListItem(
            foodUI = previewFood.toFoodUI(),
            onClick = {}
        )
    }
}

internal val previewFood = com.assessment.voyatek.features.home.domain.Food(
    id = 1,
    foodImage = "https://media.cnn.com/api/v1/images/stellar/prod/gettyimages-1273516682.jpg",
    foodName = "Garlic Butter Shrimp Pasta",
    foodCalories = 320,
    foodDescription = "Creamy hummus spread on whole grain toast topped with sliced cucumbers and radishes.",
    foodTags = listOf("healthy", "vegetarian"),
    foodImageList = emptyList()
)