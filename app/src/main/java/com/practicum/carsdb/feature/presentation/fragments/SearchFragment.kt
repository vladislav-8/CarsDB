package com.practicum.carsdb.feature.presentation.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.practicum.carsdb.R
import com.practicum.carsdb.core.diffutil.CarAdapter
import com.practicum.carsdb.core.utils.BUNDLE_KEY
import com.practicum.carsdb.core.utils.COUNTER
import com.practicum.carsdb.core.utils.IS_ENABLED
import com.practicum.carsdb.core.utils.IS_FOLLOW
import com.practicum.carsdb.core.utils.SP
import com.practicum.carsdb.core.utils.isEnabled
import com.practicum.carsdb.databinding.FragmentSearchBinding
import com.practicum.carsdb.feature.domain.models.Car
import com.practicum.carsdb.feature.presentation.models.CarState
import com.practicum.carsdb.feature.presentation.viewmodels.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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
            Car(
                id = 1,
                name = "Honda Fit",
                R.drawable.ic_honda.toString(),
                2021,
                1.3f,
                "11/09/2021"
            )
        val car1: Car =
            Car(
                id = 2,
                name = "Honda Jazz",
                R.drawable.ic_honda.toString(),
                2022,
                1.5f,
                "11/09/2022"
            )
        val car2: Car =
            Car(
                id = 3,
                name = "Honda Accord",
                R.drawable.ic_honda.toString(),
                2023,
                2.0f,
                "11/09/2023"
            )
        val car3: Car =
            Car(
                id = 4,
                name = "Honda Civic",
                R.drawable.ic_honda.toString(),
                2024,
                1.8f,
                "11/09/2024"
            )
        val car4: Car =
            Car(
                id = 5,
                name = "Honda CR-V",
                R.drawable.ic_honda.toString(),
                2025,
                2.0f,
                "11/09/2025"
            )
        viewModel.addCar(car0)
        viewModel.addCar(car1)
        viewModel.addCar(car2)
        viewModel.addCar(car3)
        viewModel.addCar(car4)
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
            viewModel.counterAddItem++
            viewModel.saveSettings(SP, viewModel.counterAddItem)
        }

        searchBinding.settingsIv.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
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

        findNavController().navigate(
            R.id.carInformationFragment,
            createArgs(car)
        )

        viewModel.counterOpenItem++
        if (viewModel.counterOpenItem == 3) {
           viewModel.setBoolean(IS_ENABLED, false)
            isEnabled = false
        }
        viewModel.saveSettings(COUNTER, viewModel.counterOpenItem)

    }

    override fun onResume() {
        super.onResume()

        if (!viewModel.getBoolean(IS_FOLLOW)) {

            val counter = viewModel.getSettings(SP, 0)
            val openCounter = viewModel.getSettings(COUNTER, 0)

            searchBinding.fabAdd.isEnabled = counter < 2
            viewModel.getBoolean(IS_ENABLED)

            if (openCounter == 3 && counter == 2) {
                PopUpFragment().show(childFragmentManager, TAG_POPUP)
            }
        } else {
            searchBinding.fabAdd.isEnabled
            isEnabled = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _searchBinding = null
    }

    companion object {
        fun createArgs(car: Car): Bundle {
            return bundleOf(BUNDLE_KEY to car)
        }
        const val TAG_POPUP = "pop_up"
    }
}