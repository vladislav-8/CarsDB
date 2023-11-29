package com.practicum.carsdb.feature.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.practicum.carsdb.feature.domain.usecase.SetBoolUseCase

class PopUpViewModel(
    private val setBoolUseCase: SetBoolUseCase
) : ViewModel() {

    fun setBoolean(key: String, defValue: Boolean) {
        setBoolUseCase.invoke(key, defValue)
    }
}