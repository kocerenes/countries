package com.example.countriesapp.presentation.country_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.R
import com.example.countriesapp.model.Country
import kotlinx.android.synthetic.main.countries_recycler_row.view.*

class CountryListRecyclerAdapter(val countryList : ArrayList<Country>)
    :RecyclerView.Adapter<CountryListRecyclerAdapter.CountryViewHolder>(){

        class CountryViewHolder(var view : View) : RecyclerView.ViewHolder(view){

        }

    private val diffUtil = object : DiffUtil.ItemCallback<Country>(){
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

    var diffList : List<Country>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.countries_recycler_row,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.tvName.text = countryList[position].name
        holder.view.tvRegion.text = countryList[position].region
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

}