package com.practicum.carsdb.core.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practicum.carsdb.core.db.entity.CarEntity

@Dao
interface CarDao {

    @Insert(entity = CarEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCar(carEntity: CarEntity)

    @Delete
    suspend fun deleteCar(carEntity: CarEntity)

    @Query("SELECT * FROM ${CarEntity.TABLE_NAME} ORDER BY name DESC")
    suspend fun getAllCars(): List<CarEntity>
}