package com.practicum.carsdb.core.di

import com.practicum.carsdb.feature.data.impl.CarRepositoryImpl
import com.practicum.carsdb.feature.domain.repository.CarRepository
import com.practicum.carsdb.feature.presentation.viewmodels.NewCarViewModel
import com.practicum.carsdb.feature.presentation.viewmodels.PopUpViewModel
import com.practicum.carsdb.feature.presentation.viewmodels.SearchViewModel
import com.practicum.carsdb.feature.presentation.viewmodels.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val carModule = module {

    single<CarRepository> {
        CarRepositoryImpl(database = get(), converter = get(), context = get(), sharedPreferences = get())
    }

    viewModel<SearchViewModel> {
        SearchViewModel(carRepository = get(), getSettingsUseCase = get(), saveSettingsUseCase = get())
    }

    viewModel<NewCarViewModel> {
        NewCarViewModel(carRepository = get())
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel(clearSettingsUseCase = get())
    }

    viewModel<PopUpViewModel> {
        PopUpViewModel(clearSettingsUseCase = get())
    }
}