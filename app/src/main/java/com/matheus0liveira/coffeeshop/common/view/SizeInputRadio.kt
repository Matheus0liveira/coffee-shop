package com.matheus0liveira.coffeeshop.common.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matheus0liveira.coffeeshop.R
import com.matheus0liveira.coffeeshop.databinding.ItemSizeInputRadioBinding
import com.matheus0liveira.coffeeshop.databinding.SizeInputRadioBinding


data class SizeItem(
    val label: String,
    val value: String,
    var enabled: Boolean = false
)


class SizeInputRadio : FrameLayout {
    private lateinit var binding: SizeInputRadioBinding
    lateinit var onChangeItemSize: (SizeItem) -> Unit

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setup(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setup(context, attrs)
    }


    private fun setup(context: Context, attrs: AttributeSet?) {
        val layoutInflater = LayoutInflater.from(context)
        binding = SizeInputRadioBinding.inflate(layoutInflater, this);
        val items = mutableListOf<SizeItem>()
        items.add(SizeItem("S", "s"))
        items.add(SizeItem("M", "m"))
        items.add(SizeItem("L", "l", true))
        with(binding) {
            rvSizeInputRadio.adapter = Adapter(items)
            rvSizeInputRadio.layoutManager = GridLayoutManager(context, 3)
        }

    }

    private inner class Adapter(private val items: List<SizeItem>) :
        RecyclerView.Adapter<Adapter.ViewHolder>() {
        inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            private lateinit var binding: ItemSizeInputRadioBinding

            @SuppressLint("NotifyDataSetChanged")
            fun bind(item: SizeItem) {
                with(binding) {
                    itemSizeTextTxt.text = item.label
                    itemSizeContainer.setOnClickListener {
                        if (!item.enabled) {
                            item.enabled = true
                        } else {
                            return@setOnClickListener
                        }
                        items.forEach { if (it != item && it.enabled) it.enabled = false }
                        this@Adapter.notifyDataSetChanged()

                        onChangeItemSize.invoke(item)

                        Log.i("CLICK INSIDE", "$item")
                    }
                }
            }

            fun init(item: SizeItem, position: Int) {
                binding = ItemSizeInputRadioBinding.bind(view)

                with(binding) {
                    val param = itemSizeContainer.layoutParams as MarginLayoutParams
                    if (position != 0) param.setMargins(12, 0, 0, 0)

                    if (item.enabled) {
                        itemSizeContainer.setBackgroundResource(R.drawable.item_size_enabled)
                        itemSizeTextTxt.setTextColor(
                            ContextCompat.getColor(view.context, R.color.brown)
                        )
                    } else {
                        itemSizeContainer.setBackgroundResource(R.drawable.item_size_disabled)
                        itemSizeTextTxt.setTextColor(
                            ContextCompat.getColor(view.context, R.color.black)
                        )
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_size_input_radio, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.init(items[position], position)
            holder.bind(items[position])
        }
    }
}