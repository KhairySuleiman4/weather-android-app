package com.example.weatherapp.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.pojos.local.weather.WeatherDetails
import com.example.weatherapp.model.repos.AppRepoImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repo: AppRepoImp) : ViewModel() {

    private val mutableFavCities = MutableStateFlow<List<WeatherDetails>>(emptyList())
    val favCities: StateFlow<List<WeatherDetails>> = mutableFavCities.asStateFlow()
    private val mutableIsConnected = MutableStateFlow(false)
    val isConnected = mutableIsConnected.asStateFlow()

    init {
        getFavCities()
        isConnectedToInternet()
    }

    private fun getFavCities() {
        viewModelScope.launch {
            repo.getAllFavoriteWeatherDetails()
                .collect {
                    mutableFavCities.value = it
                }
        }
    }

    fun deleteCity(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteFavoriteCityWeather(id)
        }
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteFavoriteCityForecasts(id)
        }
    }

    fun isConnectedToInternet(){
        mutableIsConnected.value = repo.isInternetAvailable()
    }
}

class FavoriteFactory(private val repo: AppRepoImp) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteViewModel(repo) as T
    }
}