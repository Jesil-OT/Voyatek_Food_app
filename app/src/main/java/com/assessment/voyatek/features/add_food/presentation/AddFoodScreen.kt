package com.assessment.voyatek.features.add_food.presentation

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assessment.voyatek.R
import com.assessment.voyatek.core.presentation.components.LoadingBox
import com.assessment.voyatek.core.presentation.util.ObserveAsEvents
import com.assessment.voyatek.core.presentation.util.PhotoManager
import com.assessment.voyatek.core.presentation.util.toLocalizedString
import com.assessment.voyatek.features.add_food.presentation.components.AddFoodTopBar
import com.assessment.voyatek.features.add_food.presentation.components.BasicTextWithHeader
import com.assessment.voyatek.features.add_food.presentation.components.ImageComponent
import com.assessment.voyatek.features.add_food.presentation.components.ImageItemButton
import com.assessment.voyatek.features.add_food.presentation.components.OutlineExposedDropdownMenuBox
import com.assessment.voyatek.features.add_food.presentation.components.OutlineExposedDropdownTags
import com.assessment.voyatek.features.add_food.presentation.model.AddFoodUI
import com.assessment.voyatek.features.theme.VoyatekTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun AddFoodScreen(
    modifier: Modifier = Modifier,
    addFoodState: AddFoodUI,
    addFoodButtonState: Boolean,
    setImageUris: (List<Uri>) -> Unit,
    onAction: (AddFoodScreenAction) -> Unit,
    events: Flow<AddFoodEvents>,
    onBackPress: () -> Unit
) {
    val context = LocalContext.current
    var photoUri by remember { mutableStateOf<Uri?>(null) }

    val uploadImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uris ->
            if (uris.isNotEmpty()) {
                setImageUris(uris)
            } else {
                Toast.makeText(context, "No image was selected", Toast.LENGTH_LONG).show()
            }
        }

    val requestCameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                photoUri?.let {
                    setImageUris(listOf(it))
                }
            } else {
                Toast.makeText(context, "Camera permission is required", Toast.LENGTH_LONG).show()
            }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                photoUri?.let { setImageUris(listOf(it)) }
            } else {
                Toast.makeText(context, "Failed to capture photo!", Toast.LENGTH_LONG).show()
            }
        }
    )

    ObserveAsEvents(
        events = events,
        onEvent = { event ->
            when (event) {
                is AddFoodEvents.Error -> {
                    Toast.makeText(
                        context,
                        event.message toLocalizedString context,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                is AddFoodEvents.Success -> {
                    Toast.makeText(context, "Food added successfully!", Toast.LENGTH_SHORT).show()
                    onBackPress()
                }
            }
        }
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.navigationBars,
        topBar = {
            AddFoodTopBar(onBackClick = onBackPress)
        },
        content = { innerPadding ->
            if (addFoodState.isLoading) {
                LoadingBox(modifier.fillMaxSize())
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    content = {
                        // needed for button alignment
                       Column(
                            modifier = Modifier
                                .padding(innerPadding)
                                .verticalScroll(rememberScrollState())
                                .weight(1f, false),
                           content = {
                               Row(
                                   horizontalArrangement = Arrangement.spacedBy(8.dp),
                                   content = {
                                       ImageItemButton(
                                           modifier = Modifier.weight(1f),
                                           onClick = {
                                               PhotoManager.takePhoto(
                                                   context = context,
                                                   cameraLauncher = cameraLauncher,
                                                   permissionLauncher = requestCameraPermissionLauncher,
                                                   onCapturePhotoUri = { photoUri = it }
                                               )
                                           },
                                           text = stringResource(R.string.take_photo),
                                           image = painterResource(R.drawable.camera_icon),
                                           contentDescription = stringResource(R.string.open_camara)
                                       )
                                       ImageItemButton(
                                           modifier = Modifier.weight(1f),
                                           onClick = {
                                               PhotoManager.uploadPhoto(
                                                   uploadImageLauncher = uploadImageLauncher
                                               )
                                           },
                                           text = stringResource(R.string.upload),
                                           image = painterResource(R.drawable.upload_icon),
                                           contentDescription = stringResource(R.string.choose_from_gallery)
                                       )
                                   }
                               )
                               Spacer(Modifier.height(12.dp))
                               LazyRow(
                                   modifier = Modifier.fillMaxWidth(),
                                   horizontalArrangement = Arrangement.spacedBy(8.dp)
                               ) {
                                   items(items = addFoodState.imagesUrl, key = { it }) { image ->
                                       ImageComponent(
                                           modifier = Modifier.animateItem(),
                                           image = image,
                                           onDeleteClick = {
                                               onAction(AddFoodScreenAction.OnDeleteImage(image))
                                           })
                                   }
                               }

                               Spacer(Modifier.height(12.dp))
                               BasicTextWithHeader(
                                   headerTitle = stringResource(R.string.name),
                                   onValueChange = { onAction(AddFoodScreenAction.OnNameChange(it)) },
                                   text = addFoodState.foodName,
                                   minLines = 1,
                                   placeholder = stringResource(R.string.enter_food_name)
                               )

                               Spacer(modifier = Modifier.height(16.dp))

                               BasicTextWithHeader(
                                   headerTitle = stringResource(R.string.descripton),
                                   onValueChange = { onAction(AddFoodScreenAction.OnDescriptionChange(it)) },
                                   text = addFoodState.foodDescription,
                                   singleLine = false,
                                   minLines = 3,
                                   placeholder = stringResource(R.string.enter_food_description)
                               )

                               Spacer(modifier = Modifier.height(16.dp))

                               var expandedCategory by remember { mutableStateOf(false) }
                               OutlineExposedDropdownMenuBox(
                                   headerTitle = stringResource(R.string.category),
                                   placeholder = stringResource(R.string.select_a_category),
                                   expanded = expandedCategory,
                                   value = addFoodState.foodCategory,
                                   onExpandedChange = {
                                       expandedCategory = it
                                   },
                                   onSelectedCategory = {
                                       onAction(
                                           AddFoodScreenAction.OnCategorySelected(it)
                                       )
                                   },
                                   onDismissCategories = {
                                       expandedCategory = false
                                   },
                               )

                               Spacer(modifier = Modifier.height(16.dp))

                               BasicTextWithHeader(
                                   text = addFoodState.foodCalories,
                                   headerTitle = stringResource(R.string.calories),
                                   onValueChange = { onAction(AddFoodScreenAction.OnCaloriesChange(it)) },
                                   placeholder = stringResource(R.string.enter_food_description),
                                   keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                   singleLine = true
                               )

                               Spacer(modifier = Modifier.height(16.dp))

                               var expandedTag by remember { mutableStateOf(false) }
                               OutlineExposedDropdownTags(
                                   headerTitle = stringResource(R.string.tags),
                                   expanded = expandedTag,
                                   menuItems = addFoodState.tags.toList(),
                                   value = addFoodState.selectedTags.toList(),
                                   onExpandedChange = { expandedTag = it },
                                   onDismissTagMenu = { expandedTag = false },
                                   onSelectedTag = { tag -> onAction(AddFoodScreenAction.OnTagSelected(tag)) },
                                   onRemoveTag = { tag -> onAction(AddFoodScreenAction.OnDeleteTag(tag)) }
                               )

                               Spacer(modifier = Modifier.height(4.dp))

                               Text(
                                   text = stringResource(R.string.tag_message),
                                   style = MaterialTheme.typography.labelSmall,
                                   fontSize = 12.sp
                               )

                               Spacer(modifier = Modifier.weight(1f))

                           }
                       )

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 50.dp),
                            enabled = addFoodButtonState,
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0D6EFD),
                                disabledContainerColor = Color(0xFFE7F0FF),
                                disabledContentColor = Color(0xFF98A2B3)
                            ),
                            onClick = { onAction(AddFoodScreenAction.OnCreateClick) },
                            content = {
                                Text(
                                    text = stringResource(R.string.add_food),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (addFoodButtonState) Color(0xFF98A2B3) else Color.White
                                )
                            }
                        )
                    }
                )
            }
        }
    )
}

@Preview
@Composable
private fun AddFoodScreenPreview() {
    VoyatekTheme {
        AddFoodScreen(
            addFoodState = AddFoodUI(),
            addFoodButtonState = false,
            setImageUris = {},
            onAction = {},
            onBackPress = {},
            events = flowOf()
        )
    }
}