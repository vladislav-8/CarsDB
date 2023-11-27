package com.practicum.carsdb.feature.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.practicum.carsdb.databinding.FragmentSearchBinding
import com.practicum.carsdb.feature.domain.models.Car
import com.practicum.carsdb.feature.presentation.adapter.CarAdapter
import com.practicum.carsdb.feature.presentation.viewmodels.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class SearchFragment : Fragment() {

    private var _searchBinding: FragmentSearchBinding? = null
    private val searchBinding get() = _searchBinding!!
    private val viewModel by viewModel<SearchViewModel>()

    private var carList = ArrayList<Car>()
    val carAdapter = CarAdapter(carList)

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

        addDataToList()
        initView()
    }

    private fun initView() {
        searchBinding.searchRecycler.adapter = carAdapter

        searchBinding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<Car>()
            for (i in carList) {
                if (i.name.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                carAdapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        carList.add(Car(name = "Toyota Camry", "", 2023, 2.5f, "11.09.01"))
        carList.add(Car(name = "Honda Accord", "", 2023, 2.5f, "11.09.01"))
        carList.add(Car(name = "Subaru Levorg", "", 2023, 2.5f, "11.09.01"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _searchBinding = null
    }

}