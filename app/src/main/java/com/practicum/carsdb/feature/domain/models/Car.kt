package com.practicum.carsdb.feature.domain.models

import java.io.Serializable

data class Car(
    val name: String,
    val imageUri: String,
    val year: Int,
    val engine: Float,
    val date: String
) : Serializable