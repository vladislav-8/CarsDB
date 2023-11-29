package com.practicum.carsdb.feature.domain.usecase

import com.practicum.carsdb.feature.domain.repository.CarRepository

class ClearSettingsUseCase(private val carRepository: CarRepository) {

    operator fun invoke() = carRepository.clearSettings()
}