package com.example.weatherapp.ui.city

import android.content.Intent
import android.os.CountDownTimer
import android.os.Handler
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class CityFragment : BaseFragment(R.layout.fragment_city) {
    private lateinit var cAdapter: CityAdapter
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private val viewModel: CityViewModel by viewModel()

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
        searchView.queryHint = "Введите название города"

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
