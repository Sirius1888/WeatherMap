package com.example.weatherapp.ui.city

import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.model.city.CityModel
import com.example.weatherapp.ui.detail_city.DetailCityActivity
import org.koin.android.ext.android.inject

class CityFragment : BaseFragment<CityViewModel>(R.layout.fragment_city) {
    private lateinit var cAdapter: CityAdapter
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView

    override val viewModel by inject<CityViewModel>()

    override fun initViews(view: View) {
        searchView = view.findViewById(R.id.search_view)
        recyclerView = view.findViewById(R.id.recycler_view)
        initRecycler()
        getCityData()
    }

    override fun loadingStatus() {
        viewModel.loading.observe(this@CityFragment, Observer {
            Log.v("viewmodel state: ", viewModel.loading.value.toString())
        })
    }

    private fun initRecycler() {
        cAdapter = CityAdapter(this@CityFragment::onClickItem)
        recyclerView.apply {
            adapter = cAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun onClickItem(city: CityModel) {
        val intent = Intent(context, DetailCityActivity::class.java)
        intent.putExtra("city", city.flag)
        startActivity(intent)
    }

    private fun getCityData() {
        searchView.queryHint = stringFromResources(R.string.type_name_of_city)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                val timer = object : CountDownTimer(800, 1000) {

                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        viewModel.loading.value = true

                        viewModel.getCityData(newText)
                        viewModel.cities.observe(viewLifecycleOwner, Observer {
                            if (!it.isNullOrEmpty())
                                cAdapter.updateList(it as MutableList<CityModel>)
                        })
                    }
                }
                timer.start()
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })
    }

}
