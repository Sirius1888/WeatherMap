package com.example.weatherapp.ui.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.city.CityModel

class CityAdapter(private val function: (CityModel) -> Unit) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    private var cityList: MutableList<CityModel>? = null

    fun updateList(list: MutableList<CityModel>) {
        cityList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view: View = LayoutInflater.from(parent.context!!)
            .inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view, function)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cityList!![position])
    }

    override fun getItemCount(): Int {
        if (cityList != null) {
            return cityList!!.size
        }
        return 0
    }

    inner class CityViewHolder(itemView: View, val function: (CityModel) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val cityTitle: TextView = itemView.findViewById(R.id.city_title)
        val citySubTitle: TextView = itemView.findViewById(R.id.city_sub_title)

        fun bind(city: CityModel) {
            cityTitle.text = city.name
            citySubTitle.text = city.capital

            itemView.setOnClickListener {
                function(city)
            }
        }
    }
}