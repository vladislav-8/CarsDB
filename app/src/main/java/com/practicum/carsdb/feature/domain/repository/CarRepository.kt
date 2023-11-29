package com.practicum.carsdb.feature.domain.repository

import com.practicum.carsdb.feature.domain.models.Car
import kotlinx.coroutines.flow.Flow

interface CarRepository {

    suspend fun getAllCars(): Flow<List<Car>>
    suspend fun addCar(car: Car)
    suspend fun deleteCar(car: Car)

    suspend fun saveImageToPrivateStorage(uri: String)

    fun getIntCounters(key: String?, defValue: Int): Int
    fun saveIntCounters(key: String?, value: Int)
    fun clearSettings()

    fun getBooleanFollow(key: String?): Boolean
    fun setBooleanFollow(key: String?, value: Boolean)
}