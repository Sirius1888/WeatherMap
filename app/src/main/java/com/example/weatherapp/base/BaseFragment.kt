package com.example.weatherapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.stringFromResources

abstract class BaseFragment<ViewModel: BaseViewModel>(private val layoutId: Int) : Fragment() {

    protected abstract val viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutId, container, false)
        initViews(view)
        loadingStatus()
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    abstract fun initViews(view: View)
    abstract fun loadingStatus()

    fun stringFromResources(id: Int): String? {
        return activity?.applicationContext?.stringFromResources(id)
    }

}
