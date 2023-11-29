package com.practicum.carsdb.feature.data.impl

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import androidx.core.net.toUri
import com.practicum.carsdb.core.db.converters.ConverterDb
import com.practicum.carsdb.core.db.database.CarDatabase
import com.practicum.carsdb.core.db.entity.CarEntity
import com.practicum.carsdb.core.utils.COUNTER
import com.practicum.carsdb.core.utils.IS_ENABLED
import com.practicum.carsdb.core.utils.IS_FOLLOW
import com.practicum.carsdb.core.utils.SP
import com.practicum.carsdb.feature.domain.models.Car
import com.practicum.carsdb.feature.domain.repository.CarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class CarRepositoryImpl(
    private val database: CarDatabase,
    private val converter: ConverterDb,
    private val context: Context,
    private val sharedPreferences: SharedPreferences?
) : CarRepository {

    override suspend fun getAllCars(): Flow<List<Car>> = flow {
        val cars = database.carDao().getAllCars()
        emit(convertFromCarEntity(cars))
    }

    override suspend fun addCar(car: Car) {
        database.carDao().addCar(converter.mapFromCarToCarEntity(car))
    }

    override suspend fun deleteCar(car: Car) {
        database.carDao().deleteCar(converter.mapFromCarToCarEntity(car))
    }

    override suspend fun saveImageToPrivateStorage(uri: String) {
        val filePath = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), DIRECTORY)

        if (!filePath.exists()) {
            filePath.mkdirs()
        }
        val file = File(filePath, IMAGE_NAME)
        val inputStream = context.contentResolver.openInputStream(uri.toUri())
        val outputStream = withContext(Dispatchers.IO) {
            FileOutputStream(file)
        }

        BitmapFactory
            .decodeStream(inputStream)
            .compress(Bitmap.CompressFormat.JPEG, QUALITY_IMAGE, outputStream)
    }

    override fun saveIntCounters(key: String?, value: Int) {
        val prefs: SharedPreferences = context.getSharedPreferences(SP, 0)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    override fun getIntCounters(key: String?, defValue: Int): Int {
        val prefs: SharedPreferences = context.getSharedPreferences(SP, 0)
        return prefs.getInt(key, defValue)
    }

    override fun clearSettings() {
        sharedPreferences?.edit()
            ?.putInt(SP, 0)
            ?.putInt(COUNTER, 0)
            ?.putBoolean(IS_ENABLED, false)
            ?.putBoolean(IS_FOLLOW, false)
            ?.apply()
    }

    override fun getBooleanFollow(key: String?): Boolean {
        val prefs: SharedPreferences = context.getSharedPreferences(SP, 0)
        return prefs.getBoolean(key, false)
    }

    override fun setBooleanFollow(key: String?, value: Boolean) {
        val prefs: SharedPreferences = context.getSharedPreferences(SP, 0)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun convertFromCarEntity(habits: List<CarEntity>): List<Car> {
        return habits.map { cars ->
            converter.mapFromCarEntityToCar(
                cars
            )
        }
    }

    companion object {
        private const val QUALITY_IMAGE = 30
        const val DIRECTORY = "image"
        private const val IMAGE_NAME = "imageName"
    }
}