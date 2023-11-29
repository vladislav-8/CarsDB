package com.practicum.carsdb.feature.domain.usecase

import com.practicum.carsdb.feature.domain.repository.CarRepository

class GetSettingsUseCase(val carRepository: CarRepository) {
    operator fun invoke(key: String, defValue: Int) = carRepository.getInt(key, defValue)
}