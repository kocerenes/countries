package com.example.countriesapp.presentation.country_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentCountryDetailBinding
import com.example.countriesapp.utils.downloadFromUrl
import com.example.countriesapp.utils.placeholderProgressBar
import kotlinx.android.synthetic.main.fragment_country_detail.*
import java.util.zip.Inflater


class CountryDetailFragment : Fragment() {

    private lateinit var viewModel : CountryDetailViewModel
    private var countryUuid = 0
    private lateinit var databinding : FragmentCountryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding = DataBindingUtil.inflate(inflater,R.layout.fragment_country_detail,container,false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryDetailFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProvider(this).get(CountryDetailViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                databinding.selectedCountry = it

            }
        })
    }

}