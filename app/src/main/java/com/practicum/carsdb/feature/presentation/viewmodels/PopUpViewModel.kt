package com.practicum.carsdb.feature.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.practicum.carsdb.feature.domain.usecase.ClearSettingsUseCase

class PopUpViewModel(
    private val clearSettingsUseCase: ClearSettingsUseCase
) : ViewModel() {

    fun clearSettings() {
        clearSettingsUseCase.invoke()
    }
}