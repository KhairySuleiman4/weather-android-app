package com.example.weatherapp.model.pojos

sealed class Response<out T> {
    data object Loading : Response<Nothing>()
    data class Success<out T>(val data: T) : Response<T>()
    data class Failure(val error: Throwable) : Response<Nothing>()
}