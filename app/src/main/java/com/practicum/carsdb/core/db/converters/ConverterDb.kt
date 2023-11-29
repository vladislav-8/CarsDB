package com.practicum.carsdb.core.db.converters

import com.practicum.carsdb.core.db.entity.CarEntity
import com.practicum.carsdb.feature.domain.models.Car

class ConverterDb {

    fun mapFromCarEntityToCar(from: CarEntity): Car {
        return Car(
            id = from.id,
            name = from.name,
            date = from.date,
            engine = from.engine,
            imageUri = from.imageUri,
            year = from.year
        )
    }

    fun mapFromCarToCarEntity(from: Car): CarEntity {
        return CarEntity(
            name = from.name,
            date = from.date,
            engine = from.engine,
            imageUri = from.imageUri,
            year = from.year,
            id = from.id
        )
    }
}