package com.assessment.voyatek.features.home.data.networking

import com.assessment.voyatek.core.data.networking.constructUrl
import com.assessment.voyatek.core.data.networking.safeCall
import com.assessment.voyatek.core.domain.util.NetworkError
import com.assessment.voyatek.core.domain.util.Result
import com.assessment.voyatek.core.domain.util.map
import com.assessment.voyatek.features.home.data.mapper.toFood
import com.assessment.voyatek.features.home.data.networking.dto.FoodResponseDto
import com.assessment.voyatek.features.home.domain.Food
import com.assessment.voyatek.features.home.domain.FoodDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteFoodDataSource(
    private val httpClient: HttpClient
) : FoodDataSource {
    override suspend fun getFoodList(): Result<List<Food>, NetworkError> {
        return safeCall<FoodResponseDto> {
            httpClient.get(
                urlString = constructUrl("api/foods")
            )
        }.map { response ->
            response.foodData.map { it.toFood() }
        }
    }
}