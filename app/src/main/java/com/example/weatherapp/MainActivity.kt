package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.weatherapp.ui.city.CityFragment
import com.example.weatherapp.ui.map.MapFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSelectFragment(MapFragment())
        setupNavigationView()
    }

    private fun setupNavigationView() {
        navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.n_map -> {
                    setSelectFragment(MapFragment())
                    true
                }
                R.id.n_city_search -> {
                    setSelectFragment(CityFragment())
                    true
                }
                R.id.n_profile -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun setSelectFragment(fr: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_fragment, fr).addToBackStack(fr.tag).commit()
    }

    /*
    1) Переписать все запросы на корутины.
    2) Добавить сохранение мест в бд.
    3) Сделать удаление из бд.
    4) Сделать запрос на получение всех элементов из бд и отобразить их на карте.
     */
}
