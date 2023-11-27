package com.practicum.carsdb.core.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practicum.carsdb.core.db.dao.CarDao
import com.practicum.carsdb.core.db.entity.CarEntity

@Database(
    entities = [CarEntity::class],
    version = 1
)
abstract class CarDatabase: RoomDatabase() {

    abstract fun carDao(): CarDao
}