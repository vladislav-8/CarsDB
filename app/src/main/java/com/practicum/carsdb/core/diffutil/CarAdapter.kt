package com.practicum.carsdb.core.diffutil

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practicum.carsdb.R
import com.practicum.carsdb.core.utils.isEnabled
import com.practicum.carsdb.feature.domain.models.Car
import java.util.Locale

class CarAdapter(
    private val clickListener: CarClickListener,
    private val longClick: LongCarClickListener,
) :
    RecyclerView.Adapter<CarViewHolder>(), Filterable {

    internal var cars = mutableListOf<Car>()
        set(newCars) {
            val diffCallback = DiffUtilCallback(field, newCars)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newCars
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount() = cars.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(cars[position])
        if (isEnabled)
        holder.itemView.setOnClickListener {
            clickListener.onCarClick(cars[holder.adapterPosition])
        } else {
            //
        }
        holder.itemView.setOnLongClickListener {
            longClick.onCarLongClick(cars[holder.adapterPosition])
            true
        }
    }

    fun interface CarClickListener {
        fun onCarClick(car: Car)
    }

    fun interface LongCarClickListener {
        fun onCarLongClick(car: Car)
    }

    fun clearCars() {
        cars = ArrayList()
    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Car>()
            if (constraint.isNullOrEmpty()) {
                filteredList.addAll(cars)
            } else {
                val filterPattern =
                    constraint.toString().lowercase(Locale.ITALIAN).trim { it <= ' ' }
                for (item in cars) {
                    if (item.name.lowercase(Locale.ITALIAN).contains(filterPattern)
                        || item.date.lowercase(Locale.ITALIAN).contains(filterPattern)
                    ) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            cars = ArrayList()
            cars.addAll(filterResults?.values as MutableList<Car>)
        }
    }
}


