package com.practicum.carsdb.feature.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.practicum.carsdb.R
import com.practicum.carsdb.core.utils.BUNDLE_KEY
import com.practicum.carsdb.databinding.FragmentCarInformationBinding
import com.practicum.carsdb.feature.domain.models.Car

class CarInformationFragment : Fragment() {

    private var _carInformationBinding: FragmentCarInformationBinding? = null
    private val carInformationBinding get() = _carInformationBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _carInformationBinding = FragmentCarInformationBinding.inflate(inflater, container, false)
        return carInformationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val car = requireArguments().getSerializable(BUNDLE_KEY) as Car
        showCar(car)
    }

    private fun showCar(car: Car) {
        carInformationBinding.apply {
            Glide
                .with(imageView)
                .load(car.imageUri)
                .placeholder(R.drawable.ic_honda)
                .centerCrop()
                .transform(RoundedCorners(resources.getDimensionPixelSize(R.dimen.cornerRadius)))
                .into(imageView)
            name.text = "Car: ${car.name}"
            year.text = "Year: ${car.year}"
            engine.text = "Engine: ${car.engine}"
            date.text = "Date: ${car.date}"

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _carInformationBinding = null
    }
}