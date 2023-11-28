package com.practicum.carsdb.feature.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.carsdb.feature.domain.models.Car
import com.practicum.carsdb.feature.domain.repository.CarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewCarViewModel(
    private val carRepository: CarRepository
) : ViewModel() {

    fun addCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            carRepository.addCar(car)
        }
    }

    fun saveToLocalStorage(uri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            carRepository.saveImageToPrivateStorage(uri)
        }
    }
}