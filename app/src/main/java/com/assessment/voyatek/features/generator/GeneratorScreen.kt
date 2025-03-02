package com.assessment.voyatek.features.generator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GeneratorScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Generator Screen",
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier.padding(24.dp),
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}