package com.practicum.carsdb.core.di

import android.content.Context
import android.content.SharedPreferences
import com.practicum.carsdb.core.utils.SP
import com.practicum.carsdb.feature.domain.usecase.ClearSettingsUseCase
import com.practicum.carsdb.feature.domain.usecase.GetSettingsUseCase
import com.practicum.carsdb.feature.domain.usecase.SaveSettingsUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val spModule = module {

    single<SharedPreferences> {
        androidContext().getSharedPreferences(SP, Context.MODE_PRIVATE)
    }

    factory<GetSettingsUseCase> {
        GetSettingsUseCase(carRepository = get())
    }

    factory<SaveSettingsUseCase> {
        SaveSettingsUseCase(carRepository = get())
    }

    factory<ClearSettingsUseCase> {
        ClearSettingsUseCase(carRepository = get())
    }
}