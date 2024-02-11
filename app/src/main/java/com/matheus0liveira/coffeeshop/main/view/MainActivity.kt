package com.matheus0liveira.coffeeshop.main.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.marginRight
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matheus0liveira.coffeeshop.R
import com.matheus0liveira.coffeeshop.databinding.ActivityMainBinding


data class CategoryItem(
    val label: String,
    var enabled: Boolean = false
)

data class CoffeeItem(
    val title: String,
    val helpText: String,
    val price: String,
    @DrawableRes val img: Int
)


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
            val categoryList = mutableListOf<CategoryItem>()
            categoryList.add(CategoryItem("Capuccino", true))
            categoryList.add(CategoryItem("Latte"))
            categoryList.add(CategoryItem("Espresso"))
            categoryList.add(CategoryItem("Macchiato"))
            categoryList.add(CategoryItem("Americano"))
            categoryList.add(CategoryItem("Mocha"))
            categoryList.add(CategoryItem("Affogato"))
            categoryList.add(CategoryItem("Flat White"))
            categoryList.add(CategoryItem("Ristretto"))
            categoryList.add(CategoryItem("Frappé"))
            categoryList.add(CategoryItem("Irish Coffee"))
            mainCategoryList.adapter = CategoryAdapter(categoryList)
            mainCategoryList.layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)


            val coffeeList = mutableListOf<CoffeeItem>()
            coffeeList.add(
                CoffeeItem(
                    "Capuccino",
                    "with Chocolate",
                    "$ 4.33",
                    R.drawable.big_coffee
                )
            )
            coffeeList.add(CoffeeItem("Latte", "with Chocolate", "$ 4.33", R.drawable.big_coffee))
            coffeeList.add(
                CoffeeItem(
                    "Espresso",
                    "with Chocolate",
                    "$ 4.33",
                    R.drawable.big_coffee
                )
            )
            coffeeList.add(
                CoffeeItem(
                    "Macchiato",
                    "with Chocolate",
                    "$ 4.33",
                    R.drawable.big_coffee
                )
            )
            coffeeList.add(
                CoffeeItem(
                    "Americano",
                    "with Chocolate",
                    "$ 4.33",
                    R.drawable.big_coffee
                )
            )
            coffeeList.add(CoffeeItem("Mocha", "with Chocolate", "$ 4.33", R.drawable.big_coffee))
            coffeeList.add(
                CoffeeItem(
                    "Affogato",
                    "with Chocolate",
                    "$ 4.33",
                    R.drawable.big_coffee
                )
            )
            coffeeList.add(
                CoffeeItem(
                    "Flat White",
                    "with Chocolate",
                    "$ 4.33",
                    R.drawable.big_coffee
                )
            )
            coffeeList.add(
                CoffeeItem(
                    "Ristretto",
                    "with Chocolate",
                    "$ 4.33",
                    R.drawable.big_coffee
                )
            )
            coffeeList.add(CoffeeItem("Frappé", "with Chocolate", "$ 4.33", R.drawable.big_coffee))
            coffeeList.add(
                CoffeeItem(
                    "Irish Coffee",
                    "with Chocolate",
                    "$ 4.33",
                    R.drawable.big_coffee
                )
            )

            mainRvCoffeeList.adapter = CoffeeListAdapter(coffeeList)
            mainRvCoffeeList.layoutManager = GridLayoutManager(this@MainActivity, 2)
        }

    }

    class CategoryAdapter(private val items: List<CategoryItem>) :
        RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
        inner class CategoryViewHolder(private val ctx: Context, itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            @SuppressLint("NotifyDataSetChanged")
            fun bind(item: CategoryItem) {
                val txtCategory = itemView.findViewById<TextView>(R.id.category_name)
                val isEnabled = item.enabled
                txtCategory.text = item.label

                txtCategory.setOnClickListener {
                    if (!item.enabled) item.enabled = true
                    else return@setOnClickListener

                    items.forEach {
                        if (it != item && it.enabled) it.enabled = false
                    }

                    this@CategoryAdapter.notifyDataSetChanged()
                }

                if (!isEnabled) {
                    txtCategory.background.setTint(ContextCompat.getColor(ctx, R.color.white))
                    txtCategory.setTextColor(ContextCompat.getColor(ctx, R.color.green_dark))
                    txtCategory.typeface = ResourcesCompat.getFont(ctx, R.font.sora)
                } else {
                    txtCategory.background.setTint(ContextCompat.getColor(ctx, R.color.brown))
                    txtCategory.setTextColor(ContextCompat.getColor(ctx, R.color.white))
                    txtCategory.typeface = ResourcesCompat.getFont(ctx, R.font.sora_bold)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
            return CategoryViewHolder(parent.context, view)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            holder.bind(items[position])
        }
    }

    class CoffeeListAdapter(private val items: List<CoffeeItem>) :
        RecyclerView.Adapter<CoffeeListAdapter.CoffeeListViewHolder>() {
        inner class CoffeeListViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            @SuppressLint("NotifyDataSetChanged")
            fun bind(item: CoffeeItem, position: Int) {
                val param = itemView.layoutParams as ViewGroup.MarginLayoutParams
                if (position % 2 != 0) {
                    param.setMargins(8, 16, 0, 0)
                } else {
                    param.setMargins(0, 16, 8, 0)
                }
                itemView.layoutParams = param
                itemView.findViewById<ImageView>(R.id.item_img)
                    .setImageResource(item.img)
                itemView.findViewById<TextView>(R.id.item_title_txt).text = item.title
                itemView.findViewById<TextView>(R.id.item_help_text_txt).text = item.helpText
                itemView.findViewById<TextView>(R.id.item_price_txt).text = item.price
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeListViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_coffee, parent, false)
            return CoffeeListViewHolder(view)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: CoffeeListViewHolder, position: Int) =
            holder.bind(items[position], position)
    }
}