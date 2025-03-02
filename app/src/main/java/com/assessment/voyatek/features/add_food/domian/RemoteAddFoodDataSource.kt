package com.assessment.voyatek.features.add_food.domian

import android.content.Context
import android.net.Uri
import android.util.Log
import com.assessment.voyatek.core.data.networking.constructUrl
import com.assessment.voyatek.core.data.networking.safeCall
import com.assessment.voyatek.core.domain.util.NetworkError
import com.assessment.voyatek.core.domain.util.Result
import com.assessment.voyatek.core.domain.util.map
import com.assessment.voyatek.core.presentation.util.PhotoManager
import com.assessment.voyatek.features.add_food.data.mapper.toTags
import com.assessment.voyatek.features.add_food.data.networking.dto.TagsDto
import com.assessment.voyatek.features.add_food.data.networking.dto.TagsResponseDto
import com.assessment.voyatek.features.home.data.mapper.toFood
import com.assessment.voyatek.features.home.data.networking.dto.FoodResponseDto
import com.assessment.voyatek.features.home.data.networking.dto.SingleFoodResponseDto
import com.assessment.voyatek.features.home.domain.Food
import com.assessment.voyatek.features.home.presentation.model.toFoodUI
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.append
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import java.io.File

class RemoteAddFoodDataSource(
    private val httpClient: HttpClient,
    private val context: Context
) : AddFoodDataSource {
    override suspend fun addFood(
        image: List<Uri>,
        name: String,
        description: String,
        categoryId: Int,
        calories: Int,
        category: String,
        tags: List<String>
    ): Result<Food, NetworkError> {
        return safeCall<SingleFoodResponseDto> {
            httpClient.post(
                urlString = constructUrl("api/foods")
            ) {
                setBody(MultiPartFormDataContent(
                    formData {
                        append("name", name)
                        append("description", description)
                        append("category_id", categoryId.toString())
                        append("category", category)
                        append("calories", calories.toString())
                        // Uploading a file
                        image.mapIndexed { index, uri ->
                            val file = PhotoManager.uriToFile(context, uri)
                            Log.d("RemoteAddFoodDataSource", "addFood: Image file is $file")
                            println("Image file is $file")
                            append("images[$index]", file.readBytes(),
                                Headers.build {
                                    append(HttpHeaders.ContentType, PhotoManager.getMediaTypeForFile(file))
                                    append(HttpHeaders.ContentDisposition, "form-data; name=\"images[$index]\"; filename=\"${file.name}\"")
                                }
                            )
                        }
                        tags.mapIndexed { index, tags ->
                            append("tags[$index]", tags)
                        }
                    }
                ))
            }
        }.map { response ->
            response.foodData.toFood()
        }
    }

    override suspend fun getTags(): Result<List<Tags>, NetworkError> {
        return safeCall<TagsResponseDto> {
            httpClient.get(
                urlString = constructUrl("api/tags")
            )
        }.map { response ->
            response.data.map { it.toTags() }
        }
    }
}