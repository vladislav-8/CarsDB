package com.practicum.carsdb.feature.presentation.models

import com.practicum.carsdb.feature.domain.models.Car

sealed interface CarState {

    data class Content(
        val cars: List<Car>
    ) : CarState

    data object Empty : CarState
}