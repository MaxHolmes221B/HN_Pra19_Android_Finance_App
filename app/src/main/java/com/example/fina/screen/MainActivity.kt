package com.example.fina.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fina.R
import com.example.fina.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}