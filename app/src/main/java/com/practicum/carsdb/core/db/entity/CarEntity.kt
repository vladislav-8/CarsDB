package com.practicum.carsdb.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.practicum.carsdb.core.db.entity.CarEntity.Companion.TABLE_NAME
import java.io.Serializable

@Entity(tableName = TABLE_NAME)
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val imageUri: String,
    val year: Int,
    val engine: Float,
    val date: String
) : Serializable {

    companion object {
        const val TABLE_NAME = "car_table"
    }
}

