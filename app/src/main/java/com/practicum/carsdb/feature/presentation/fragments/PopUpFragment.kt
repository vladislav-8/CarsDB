package com.practicum.carsdb.feature.presentation.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.practicum.carsdb.R
import com.practicum.carsdb.core.utils.IS_FOLLOW
import com.practicum.carsdb.core.utils.isEnabled
import com.practicum.carsdb.databinding.FragmentPopUpBinding
import com.practicum.carsdb.feature.presentation.viewmodels.PopUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopUpFragment : DialogFragment() {

    private var _binding: FragmentPopUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<PopUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopUpBinding.inflate(inflater, container, false)
        dialog?.window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.noThanksButton.setOnClickListener {
            dismiss()
        }
        binding.buyButton.setOnClickListener {
            isEnabled = true
            viewModel.setBoolean(IS_FOLLOW, true)
            findNavController().navigate(R.id.searchFragment)
        }
    }
}