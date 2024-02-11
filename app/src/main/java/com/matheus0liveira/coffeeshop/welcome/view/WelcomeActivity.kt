package com.matheus0liveira.coffeeshop.welcome.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.matheus0liveira.coffeeshop.databinding.ActivityMainBinding
import com.matheus0liveira.coffeeshop.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}