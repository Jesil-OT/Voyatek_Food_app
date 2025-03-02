package com.assessment.voyatek.features.home.presentation.food_home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assessment.voyatek.R
import com.assessment.voyatek.features.theme.VoyatekTheme

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    username: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                content = {
                    val gray =  Color(0xFFF0F2F5)
                    Image(
                        painter = painterResource(R.drawable.user_avatar),
                        contentDescription = stringResource(R.string.user_avatar)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(360.dp))
                            .border(width = 1.dp, color = gray, shape = RoundedCornerShape(360.dp))
                            .clickable {  }
                            .padding(10.dp),
                        contentAlignment = Alignment.Center,
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.notification),
                                contentDescription = stringResource(R.string.general_notification)
                            )
                        }
                    )
                }
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.hey_there, username),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.welcome_description),
                style = MaterialTheme.typography.labelSmall
            )
        }
    )
}

@Preview
@Composable
private fun HomeTopBarPreview() {
    VoyatekTheme {
        HomeTopBar(
            username = "Lucy"
        )
    }
}