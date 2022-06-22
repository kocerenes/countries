package com.example.countriesapp.presentation.country_list

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriesapp.R
import kotlinx.android.synthetic.main.fragment_country_list.*


class CountryListFragment : Fragment() {

    private lateinit var viewModel : CountryListViewModel
    private val countryAdapter = CountryListRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountryListViewModel::class.java)
        viewModel.refreshData()

        rvCountryList.layoutManager = LinearLayoutManager(context)
        rvCountryList.adapter = countryAdapter


        swipeRefreshLayout.setOnRefreshListener {
            rvCountryList.visibility = View.GONE
            countryError.visibility = View.GONE
            countryLoading.visibility = View.VISIBLE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {
                rvCountryList.visibility = View.VISIBLE
                countryAdapter.diffList = it
            }
        })

        viewModel.isError.observe(viewLifecycleOwner, Observer {

            it?.let {
                if(it){
                    countryError.visibility = View.VISIBLE
                }else{
                    countryError.visibility = View.GONE
                }
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    countryLoading.visibility = View.VISIBLE
                    rvCountryList.visibility = View.GONE
                    countryError.visibility = View.GONE
                }else{
                    countryLoading.visibility = View.GONE
                }
            }
        })

    }


}