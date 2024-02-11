package com.matheus0liveira.coffeeshop.main.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.matheus0liveira.coffeeshop.R
import com.matheus0liveira.coffeeshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            mainLocationTxt.text = "Bilzen, Tanjungbalai"
            profileImage.setImageBitmap(
                AppCompatResources.getDrawable(this@MainActivity, R.drawable.big_coffee)
                    ?.toBitmap()
            )

            imgPromo.clipToOutline = true

        }

    }
}