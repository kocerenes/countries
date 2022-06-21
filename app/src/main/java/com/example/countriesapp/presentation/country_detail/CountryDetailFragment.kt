package com.example.countriesapp.presentation.country_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countriesapp.R
import kotlinx.android.synthetic.main.fragment_country_detail.*
import java.util.zip.Inflater


class CountryDetailFragment : Fragment() {

    private lateinit var viewModel : CountryDetailViewModel
    private var countryUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_detail,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CountryDetailViewModel::class.java)
        viewModel.getDataFromRoom()

        arguments?.let {
            countryUuid = CountryDetailFragmentArgs.fromBundle(it).countryUuid
        }

    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                tvCountryName.text = it.name
                tvCapital.text = it.capital
                tvCurrency.text = it.currency
                tvLanguage.text = it.language
                tvRegion.text = it.region
            }
        })
    }

}