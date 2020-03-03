package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.weatherapp.ui.MapFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        supportFragmentManager.beginTransaction().add(R.id.main_fragment, fr).commit()
    }

    /* 1. Добавить кнопку на НАШ MapFragment(), при нажатии на кнопку нужно сделать отображение
     всплывающего окна погоды.
     2. Исправить ошибку при нажатии на мап баттон

     */
}
