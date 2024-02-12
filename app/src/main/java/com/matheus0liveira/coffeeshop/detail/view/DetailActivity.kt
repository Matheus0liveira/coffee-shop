package com.matheus0liveira.coffeeshop.detail.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.matheus0liveira.coffeeshop.R
import com.matheus0liveira.coffeeshop.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            setSupportActionBar(mainToolbar)
            supportActionBar?.title = ""
            supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_left)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            detailMainImg.setImageResource(R.drawable.big_coffee)
            detailRatingAverageTxt.text = "5.0"
            detailRatingCountTxt.text = "(234)"
            detailTitleTxt.text = "Cappucino"
            detailHelpTextTxt.text = "With Chocolate"
            detailSizeRadio.onChangeItemSize = { Log.i("CLICKED", "$it") }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_bottom_favorite -> Log.i("SAVED", "${item.itemId}")
        }

        return super.onOptionsItemSelected(item)


    }
}