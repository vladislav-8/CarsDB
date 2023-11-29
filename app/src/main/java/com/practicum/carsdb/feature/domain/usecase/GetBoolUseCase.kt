package com.practicum.carsdb.feature.domain.usecase

import com.practicum.carsdb.feature.domain.repository.CarRepository

class GetBoolUseCase(private val carRepository: CarRepository) {
    operator fun invoke(key: String?) = carRepository.getBooleanFollow(key)
}