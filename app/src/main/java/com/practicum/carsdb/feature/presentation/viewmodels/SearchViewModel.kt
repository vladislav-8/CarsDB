package com.practicum.carsdb.feature.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.carsdb.feature.domain.models.Car
import com.practicum.carsdb.feature.domain.repository.CarRepository
import com.practicum.carsdb.feature.domain.usecase.GetBoolUseCase
import com.practicum.carsdb.feature.domain.usecase.GetSettingsUseCase
import com.practicum.carsdb.feature.domain.usecase.SaveSettingsUseCase
import com.practicum.carsdb.feature.domain.usecase.SetBoolUseCase
import com.practicum.carsdb.feature.presentation.models.CarState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel(
    private val carRepository: CarRepository,
    private val getSettingsUseCase: GetSettingsUseCase,
    private val saveSettingsUseCase: SaveSettingsUseCase,
    private val getBoolUseCase: GetBoolUseCase,
    private val setBoolUseCase: SetBoolUseCase
) : ViewModel() {

    var counterAddItem = 0
    var counterOpenItem = 0

    private val stateLiveData = MutableLiveData<CarState>()
    fun observeState(): LiveData<CarState> = stateLiveData

    private fun renderState(state: CarState) {
        stateLiveData.postValue(state)
    }

    fun getAllCars() {
        viewModelScope.launch {
            delay(500)
            carRepository
                .getAllCars()
                .collect { cars ->
                    if (cars.isEmpty()) {
                        renderState(CarState.Empty)
                    } else {
                        renderState(CarState.Content(cars))
                    }
                }
        }
    }

    fun addCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            carRepository.addCar(car)
        }
    }

    fun saveSettings(key: String, defValue: Int) {
        saveSettingsUseCase.invoke(key, defValue)
    }

    fun getSettings(key: String, defValue: Int): Int {
        return getSettingsUseCase.invoke(key, defValue)
    }

    fun getBoolean(key: String): Boolean {
        return getBoolUseCase.invoke(key)
    }

    fun setBoolean(key: String, defValue: Boolean) {
        setBoolUseCase.invoke(key, defValue)
    }
}