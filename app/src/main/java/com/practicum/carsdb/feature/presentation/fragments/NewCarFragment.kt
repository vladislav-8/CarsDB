package com.practicum.carsdb.feature.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.practicum.carsdb.databinding.FragmentNewCarBinding
import com.practicum.carsdb.feature.domain.models.Car
import com.practicum.carsdb.feature.presentation.viewmodels.NewCarViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewCarFragment : Fragment() {

    private var _newCarBinding: FragmentNewCarBinding? = null
    private val newCarBinding get() = _newCarBinding!!

    private val viewModel by viewModel<NewCarViewModel>()

    private var imageUri: String? = null

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            newCarBinding.pickImage.setImageURI(uri)
            imageUri = uri.toString()
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _newCarBinding = FragmentNewCarBinding.inflate(inflater, container, false)
        return newCarBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newCarBinding.pickImage.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        newCarBinding.confirmButton.setOnClickListener {
            val car = Car(
                name = newCarBinding.carnameEt.text.toString(),
                year = newCarBinding.yearEt.text.toString().toInt(),
                imageUri = imageUri!!,
                engine = newCarBinding.engineEt.text.toString().toFloat(),
                date = ""
            )

            imageUri?.let { viewModel.saveToLocalStorage(uri = it) }

            viewModel.addCar(car)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _newCarBinding = null
    }

}