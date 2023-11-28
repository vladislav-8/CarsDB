package com.practicum.carsdb.feature.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.practicum.carsdb.R
import com.practicum.carsdb.databinding.FragmentSearchBinding
import com.practicum.carsdb.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _settingsBinding: FragmentSettingsBinding? = null
    private val settingsBinding get() = _settingsBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _settingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        return settingsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsBinding.resetSettingsButton.setOnClickListener {
            //
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _settingsBinding = null
    }

}