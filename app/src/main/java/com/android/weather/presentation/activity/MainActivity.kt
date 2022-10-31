package com.android.weather.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.weather.R
import com.android.weather.databinding.ActivityMainBinding
import com.android.weather.extensions.*
import com.android.weather.presentation.fragment.BlankFragment
import com.android.weather.presentation.fragment.ListFragment
import com.android.weather.presentation.viewmodel.WeatherViewModel
import com.google.android.material.navigation.NavigationBarView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding:ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initFragment()
        binding.bottomNavigationView.setOnItemSelectedListener(selected)
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, BlankFragment.newInstance())
            .commit()
    }

    private val selected = NavigationBarView.OnItemSelectedListener { item ->
            when(item.itemId){
                R.id.home -> changeNavigationBottom(BlankFragment.newInstance())
                else -> changeNavigationBottom(ListFragment.newInstance())
            }
           true
        }

    private fun changeNavigationBottom(fragment: Fragment, tag: String = "") {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, fragment, tag)
            .commit()
    }

}