package com.example.countriesapp.presentation.country_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.R
import com.example.countriesapp.databinding.CountriesRecyclerRowBinding
import com.example.countriesapp.model.Country
import com.example.countriesapp.presentation.country_list.listener.ItemClickListener
import com.example.countriesapp.utils.downloadFromUrl
import com.example.countriesapp.utils.placeholderProgressBar
import kotlinx.android.synthetic.main.countries_recycler_row.view.*
import kotlinx.android.synthetic.main.countries_recycler_row.view.tvRegion
import kotlinx.android.synthetic.main.fragment_country_detail.view.*

class CountryListRecyclerAdapter()
    :RecyclerView.Adapter<CountryListRecyclerAdapter.CountryViewHolder>(), ItemClickListener{

        class CountryViewHolder(var binding : CountriesRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

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
        //val view = inflater.inflate(R.layout.countries_recycler_row,parent,false)
        val view = DataBindingUtil.inflate<CountriesRecyclerRowBinding>(inflater,R.layout.countries_recycler_row,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.binding.country = diffList[position]
        holder.binding.listener = this
        /*holder.view.tvName.text = diffList[position].name
        holder.view.tvRegion.text = diffList[position].region

        holder.view.setOnClickListener{
            val action = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment()
            action.countryUuid = diffList[position].uuid
            Navigation.findNavController(it).navigate(action)
        }
        diffList[position].flag?.let { imageUrl ->
            holder.view.imageView.downloadFromUrl(
                imageUrl,
                placeholderProgressBar(holder.view.context)
            )
        }*/
    }

    override fun getItemCount(): Int {
        return diffList.size
    }

    override fun onItemClick(v: View) {
        val action = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment()
        action.countryUuid = v.tvCountryUuid.text.toString().toInt()
        Navigation.findNavController(v).navigate(action)
    }

}