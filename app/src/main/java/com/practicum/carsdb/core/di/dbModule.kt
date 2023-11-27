package com.practicum.carsdb.core.di

import androidx.room.Room
import com.practicum.carsdb.core.db.database.CarDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {

    single {
        Room.databaseBuilder(androidContext(), CarDatabase::class.java, "database.db")
            .build()
    }

}