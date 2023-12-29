package com.example.foodapp.apiMinuman

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.foodapp.api.Meal
import com.example.foodapp.databinding.ItemMealBinding


val DRINK_COMPARATOR = object : DiffUtil.ItemCallback<Drink>() {
    override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean =
        // User ID serves as unique ID
        oldItem.idDrink == newItem.idDrink

    override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean =
        // Compare full contents (note: Java users should call .equals())
        oldItem == newItem
}


class DrinkAdapter(private val context: Context, private val listener: OnItemClickListener) :
    PagingDataAdapter<Drink, DrinkAdapter.ViewHolder>(DRINK_COMPARATOR) {

    inner class ViewHolder(private val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(drink: Drink) {
            Glide.with(context)
                .load(drink.strDrinkThumb)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.imgMeal)
            binding.tvMealName.text = drink.strDrink

            binding.root.setOnClickListener() {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    item?.let {
                        listener.onItemClick(it)
                    }
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drink = getItem(position)
        drink?.let {
            holder.bind(it)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(drink: Drink)
    }
}
