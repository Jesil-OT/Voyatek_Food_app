package com.assessment.voyatek.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assessment.voyatek.R
import com.assessment.voyatek.features.theme.VoyatekTheme

@Composable
fun ErrorBox(
    modifier: Modifier = Modifier,
    errorMessage: String
) {
    Box(
        modifier = modifier.verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center,
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = errorMessage,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(R.string.pull_message),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    )
}

@Preview
@Composable
fun ErrorBoxPreview() {
    VoyatekTheme {
        ErrorBox(errorMessage = "couldn't connect to server, please check your internet connection.")
    }
}