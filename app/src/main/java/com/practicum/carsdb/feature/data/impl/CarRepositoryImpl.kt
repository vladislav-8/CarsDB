package com.practicum.carsdb.feature.data.impl

import com.practicum.carsdb.core.db.converters.ConverterDb
import com.practicum.carsdb.core.db.database.CarDatabase
import com.practicum.carsdb.core.db.entity.CarEntity
import com.practicum.carsdb.feature.domain.models.Car
import com.practicum.carsdb.feature.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CarRepositoryImpl(
    private val database: CarDatabase,
    private val converter: ConverterDb
) : CarRepository {

    override suspend fun getAllCars(): Flow<List<Car>> = flow {
        val cars = database.carDao().getAllCars()
        emit(convertFromCarEntity(cars))
    }

    override suspend fun addCar(car: Car) {
        database.carDao().addCar(converter.mapFromCarToCarEntity(car))
    }

    override suspend fun deleteCar(car: Car) {
        database.carDao().deleteCar(converter.mapFromCarToCarEntity(car))
    }

    private fun convertFromCarEntity(habits: List<CarEntity>): List<Car> {
        return habits.map { cars ->
            converter.mapFromCarEntityToCar(
                cars
            )
        }
    }
}