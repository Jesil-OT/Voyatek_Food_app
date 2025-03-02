package com.assessment.voyatek.features.home.presentation.food_home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChipGroup(chipItems: List<String>, onItemClick: (String) -> Unit) {

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        content = {
            itemsIndexed(chipItems) { index, chipItem ->
                val isSelected = selectedIndex == index
                FilterChip(
                    selected = true,
                    onClick = {
                        selectedIndex = index
                        onItemClick(chipItem)
                    },
                    label = {
                        Text(
                            text = chipItem,
                            color = if (isSelected) Color.White else  Color(0xFF676E7E),
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    shape = RoundedCornerShape(4.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = if (isSelected) Color(0xFF0D6EFD) else Color(0xFFF9FAFB)
                    )
                )
            }
        }
    )
}