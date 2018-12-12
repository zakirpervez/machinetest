package com.kisanhub.demos.kisanhubdemo.activities.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kisanhub.demos.kisanhubdemo.R

class CountriesAdapter(val popupCallback: PopupCallback) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {


    private val countries: ArrayList<String> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, type: Int): CountryViewHolder {
        return CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false))
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.countryNameTv.text = countries[position]
    }


    fun update(countries: List<String>) {
        if (this.countries.isNotEmpty()) {
            this.countries.clear()
        }
        this.countries.addAll(countries)
        notifyDataSetChanged()
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryNameTv = itemView.findViewById(R.id.countryNameTv) as TextView

        init {
            itemView.setOnClickListener {
                popupCallback.onPopupCall(countries[layoutPosition], itemView)
            }
        }
    }
}

interface PopupCallback {
    fun onPopupCall(countryName: String, itemView: View)
}