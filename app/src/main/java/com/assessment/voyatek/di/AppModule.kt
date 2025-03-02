package com.assessment.voyatek.di

import com.assessment.voyatek.core.data.networking.HttpClientFactory
import com.assessment.voyatek.features.add_food.domian.AddFoodDataSource
import com.assessment.voyatek.features.add_food.domian.RemoteAddFoodDataSource
import com.assessment.voyatek.features.add_food.presentation.AddFoodViewModel
import com.assessment.voyatek.features.home.data.networking.RemoteFoodDataSource
import com.assessment.voyatek.features.home.domain.FoodDataSource
import com.assessment.voyatek.features.home.presentation.food_home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single<HttpClient> {
       HttpClientFactory.create(CIO.create())
    }
    singleOf(::RemoteFoodDataSource) bind FoodDataSource::class
    singleOf(::RemoteAddFoodDataSource) bind AddFoodDataSource::class

    viewModelOf(::HomeViewModel) bind HomeViewModel::class
    viewModelOf(::AddFoodViewModel) bind AddFoodViewModel::class
}