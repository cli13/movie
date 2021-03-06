package com.example.movietab.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movietab.adapter.PageAdapter
import com.example.movietab.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //adapter & tab setup
        binding.viewPager.adapter = PageAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Now Playing"
                }
                1 -> {
                    tab.text = "Popular Movies"
                }
            }
        }.attach()
    }
}