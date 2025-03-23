package com.example.weatherapp.model.repos

import kotlinx.coroutines.flow.Flow

interface AppRepo {
    fun readLanguageChoice(): Flow<String>
    fun readTemperatureUnit(): Flow<String>
    fun readLocationChoice(): Flow<String>
    fun readWindSpeedUnit(): Flow<String>

    suspend fun writeLanguageChoice(lang: String)
    suspend fun writeTemperatureUnit(temp: String)
    suspend fun writeLocationChoice(location: String)
    suspend fun writeWindSpeedUnit(wind: String)
}