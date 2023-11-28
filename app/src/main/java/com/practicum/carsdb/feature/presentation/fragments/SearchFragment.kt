package com.practicum.carsdb.feature.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.practicum.carsdb.R
import com.practicum.carsdb.core.diffutil.CarAdapter
import com.practicum.carsdb.core.utils.BUNDLE_KEY
import com.practicum.carsdb.databinding.FragmentSearchBinding
import com.practicum.carsdb.feature.domain.models.Car
import com.practicum.carsdb.feature.presentation.models.CarState
import com.practicum.carsdb.feature.presentation.viewmodels.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class SearchFragment : Fragment() {

    private var _searchBinding: FragmentSearchBinding? = null
    private val searchBinding get() = _searchBinding!!
    private val viewModel by viewModel<SearchViewModel>()

    private val carsAdapter by lazy {
        CarAdapter({ showCarItem(car = it) }, { })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _searchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return searchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initButtons()
        initObservers()
        addDbItems()
    }

    private fun addDbItems() {
        val car0: Car =
            Car(name = "Honda Fit", R.drawable.ic_honda.toString(), 2021, 1.3f, "11/09/2021")
        val car1: Car =
            Car(name = "Honda Jazz", R.drawable.ic_honda.toString(), 2022, 1.5f, "11/09/2022")
        val car2: Car =
            Car(name = "Honda Accord", R.drawable.ic_honda.toString(), 2023, 2.0f, "11/09/2023")
        viewModel.addCar(car2)
        viewModel.addCar(car1)
        viewModel.addCar(car0)
    }

    private fun initView() {
        searchBinding.searchRecycler.adapter = carsAdapter

        searchBinding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                carsAdapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun initButtons() {
        searchBinding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.newCarFragment)
        }
    }

    private fun initObservers() {
        viewModel.getAllCars()
        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun render(state: CarState) {
        with(searchBinding) {
            when (state) {
                is CarState.Content -> {
                    carsAdapter.clearCars()
                    carsAdapter.cars = state.cars as MutableList<Car>
                }

                is CarState.Empty -> {

                }
            }
        }
    }

    private fun showCarItem(car: Car) {
        findNavController().navigate(R.id.carInformationFragment,
            createArgs(car))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _searchBinding = null
    }

    companion object {
        fun createArgs(car: Car): Bundle {
            return bundleOf(BUNDLE_KEY to car)
        }
    }

}