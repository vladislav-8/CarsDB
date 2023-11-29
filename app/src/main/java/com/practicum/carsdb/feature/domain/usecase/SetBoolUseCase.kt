package com.practicum.carsdb.feature.domain.usecase

import com.practicum.carsdb.feature.domain.repository.CarRepository

class SetBoolUseCase(private val carRepository: CarRepository) {
    operator fun invoke(key: String, defValue: Boolean) = carRepository.setBooleanFollow(key, defValue)
}