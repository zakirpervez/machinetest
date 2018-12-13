package com.kisanhub.demos.kisanhubdemo.activities.whether.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kisanhub.demos.kisanhubdemo.R
import com.kisanhub.demos.kisanhubdemo.network.entities.WeatherInfoEntity

class WeatherInfoAdapter : RecyclerView.Adapter<WeatherInfoAdapter.WeatherInfoViewHolder>() {

    private val weatherInfoList: ArrayList<WeatherInfoEntity> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): WeatherInfoViewHolder {
        return WeatherInfoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.weather_info_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return weatherInfoList.size
    }

    override fun onBindViewHolder(holder: WeatherInfoViewHolder, position: Int) {
        holder.yearTv.setText(weatherInfoList[position].year.toString())
        holder.monthTv.setText(weatherInfoList[position].month.toString())
        holder.valueTv.setText(weatherInfoList[position].value.toString())
    }


    fun update(weatherInfoList: ArrayList<WeatherInfoEntity>) {
        if (this.weatherInfoList.isNotEmpty()) {
            this.weatherInfoList.clear()
        }
        this.weatherInfoList.addAll(weatherInfoList)
        notifyDataSetChanged()
    }


    inner class WeatherInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val yearTv = itemView.findViewById(R.id.yearTv) as TextView
        val monthTv = itemView.findViewById(R.id.monthTv) as TextView
        val valueTv = itemView.findViewById(R.id.valueTv) as TextView
    }
}