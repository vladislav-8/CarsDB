package com.practicum.carsdb.core.di

import com.practicum.carsdb.feature.data.impl.CarRepositoryImpl
import com.practicum.carsdb.feature.domain.repository.CarRepository
import com.practicum.carsdb.feature.presentation.viewmodels.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val carModule = module {

    single<CarRepository> {
        CarRepositoryImpl(database = get(), converter = get())
    }

    viewModel<SearchViewModel> {
        SearchViewModel(carRepository = get())
    }
}