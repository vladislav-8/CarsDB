package com.practicum.carsdb.core.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.practicum.carsdb.feature.domain.models.Car

class DiffUtilCallback(
    private val oldList: List<Car>,
    private val newList: List<Car>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldList = oldList[oldItemPosition]
        val newList = newList[newItemPosition]
        return oldList.name == newList.name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldList = oldList[oldItemPosition]
        val newList = newList[newItemPosition]
        return oldList == newList
    }
}