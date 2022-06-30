package com.thahira.example.officeroomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.thahira.example.officeroomapp.adapter.TabAdapter
import com.thahira.example.officeroomapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var tabTitle = arrayOf("People","Room")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pager = binding.tabDisplay
        val tl = binding.tabContent
        pager.adapter = TabAdapter(supportFragmentManager,lifecycle)

        TabLayoutMediator(tl,pager){
            tab,position ->
            tab.text = tabTitle[position]
        }.attach()

    }
}