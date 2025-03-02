package com.assessment.voyatek.features.add_food.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assessment.voyatek.R
import com.assessment.voyatek.features.add_food.presentation.model.TagUI
import com.assessment.voyatek.features.home.presentation.food_home.getAllCategories
import com.assessment.voyatek.features.theme.VoyatekTheme

@Composable
fun BasicTextWithHeader(
    modifier: Modifier = Modifier,
    headerTitle: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    text: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    singleLine: Boolean = true,
    minLines: Int = 1,
) {
    val gray = Color(0xFF98A2B3)
    Column(
        modifier = modifier,
        content = {
            Text(
                modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
                text = headerTitle,
                style = MaterialTheme.typography.labelSmall
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .border(
                        BorderStroke(
                            1.dp,
                            SolidColor(gray)
                        ),
                        RoundedCornerShape(4.dp)
                    ),
                content = {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = text,
                        textStyle = MaterialTheme.typography.labelSmall,
                        onValueChange = onValueChange,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                        ),
                        keyboardOptions = keyboardOptions,
                        minLines = minLines,
                        singleLine = singleLine,
                        placeholder = {
                            Text(
                                text = placeholder,
                                style = MaterialTheme.typography.labelSmall,
                                color = gray
                            )
                        },
                    )
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlineExposedDropdownMenuBox(
    headerTitle: String,
    expanded: Boolean,
    value: String = "",
    onExpandedChange: (Boolean) -> Unit,
    onDismissCategories: () -> Unit,
    onSelectedCategory: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    val gray = Color(0xFF98A2B3)
    Column(
        modifier = modifier,
        content = {
            Text(
                modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
                text = headerTitle,
                style = MaterialTheme.typography.labelSmall
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .border(
                        BorderStroke(
                            1.dp,
                            SolidColor(gray)
                        ),
                        RoundedCornerShape(4.dp)
                    ),
                content = {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            onExpandedChange(it)
                        },
                        content = {
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),
                                value = value,
                                onValueChange = {},
                                placeholder = {
                                    Text(
                                        text = placeholder,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = gray
                                    )
                                },
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(R.drawable.caretdown),
                                        contentDescription = stringResource(R.string.drop_down),
                                    )
                                },
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                ),
                                readOnly = true
                            )
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = onDismissCategories,
                                content = {
                                    getAllCategories.forEach {
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    text = it,
                                                    style = MaterialTheme.typography.labelSmall
                                                )
                                            },
                                            onClick = {
                                                onSelectedCategory(it)
                                                onDismissCategories()
                                            },
                                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                        )
                                    }
                                }
                            )
                        }
                    )
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlineExposedDropdownTags(
    modifier: Modifier = Modifier,
    headerTitle: String,
    expanded: Boolean,
    value: List<TagUI> = emptyList(),
    menuItems: List<TagUI> = emptyList(),
    onExpandedChange: (Boolean) -> Unit,
    onDismissTagMenu: () -> Unit,
    onSelectedTag: (TagUI) -> Unit,
    onRemoveTag: (TagUI) -> Unit,
) {
    val gray = Color(0xFF98A2B3)
    Column(
        modifier = modifier,
        content = {
            Text(
                modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
                text = headerTitle,
                style = MaterialTheme.typography.labelSmall
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .border(
                        BorderStroke(
                            1.dp,
                            SolidColor(gray)
                        ),
                        RoundedCornerShape(4.dp)
                    ),
                content = {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { onExpandedChange(it) },
                        content = {
                            TagsComponent(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),
                                tags = value,
                                onDeleteTag = { tag -> onRemoveTag(tag) }
                            )

                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = onDismissTagMenu,
                                content = {
                                    menuItems.forEach { tag ->
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    text = tag.name,
                                                    style = MaterialTheme.typography.labelSmall
                                                )
                                            },
                                            onClick = {
                                                onSelectedTag(tag)
                                                onDismissTagMenu()
                                            },
                                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                        )
                                    }
                                }
                            )
                        }
                    )
                }
            )
        }
    )
}


@Preview
@Composable
private fun BasicTextWithHeaderPreview() {
    VoyatekTheme {
        BasicTextWithHeader(
            headerTitle = stringResource(R.string.name),
            onValueChange = {},
            text = "",
            placeholder = stringResource(R.string.enter_food_name)
        )
    }
}

@Preview
@Composable
private fun OutlineExposedDropdownMenuBoxPreview() {
    VoyatekTheme {
        OutlineExposedDropdownMenuBox(
            headerTitle = stringResource(R.string.category),
            expanded = false,
            placeholder = "Dawn Delicacies",
            onExpandedChange = {},
            onDismissCategories = {},
            onSelectedCategory = {}
        )
    }
}