package com.practicum.carsdb.feature.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practicum.carsdb.R
import com.practicum.carsdb.feature.domain.models.Car

class CarAdapter(private var carList: List<Car>) :
    RecyclerView.Adapter<CarAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo : TextView = itemView.findViewById(R.id.artistName)
        val titleTv : TextView = itemView.findViewById(R.id.trackName)
    }

    fun setFilteredList(carList: List<Car>){
        this.carList = carList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item , parent , false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.logo.text = carList[position].name
        holder.titleTv.text = carList[position].date
    }

    override fun getItemCount(): Int {
        return carList.size
    }
}