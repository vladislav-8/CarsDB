package com.practicum.carsdb.core.diffutil

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.practicum.carsdb.R
import com.practicum.carsdb.feature.domain.models.Car
import java.text.SimpleDateFormat
import java.util.Locale

class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name = itemView.findViewById<TextView>(R.id.carName)
    private val year = itemView.findViewById<TextView>(R.id.yearValue)
    private val engine = itemView.findViewById<TextView>(R.id.engineValue)
    private val date = itemView.findViewById<TextView>(R.id.dateValue)
    private val carImage = itemView.findViewById<ImageView>(R.id.carImage)

    fun bind(model: Car) {
        name.text = model.name
        year.text = model.year.toString()
        engine.text = model.engine.toString()
        //date.text = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(model.date)

        Glide.with(itemView.context)
            .load(R.drawable.ic_honda)
            .centerCrop()
            .transform(RoundedCorners(itemView.resources.getDimensionPixelSize(R.dimen.cornerRadius)))
            .into(carImage)
    }
}