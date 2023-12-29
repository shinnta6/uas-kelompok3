package com.example.foodapp.meal

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


val USER_COMPARATOR = object : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        // User ID serves as unique ID
        oldItem.idMeal == newItem.idMeal

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        // Compare full contents (note: Java users should call .equals())
        oldItem == newItem
}


class MealAdapter(private val context: Context, private val listener: OnItemClickListener) :
    PagingDataAdapter<Meal, MealAdapter.ViewHolder>(USER_COMPARATOR) {

    inner class ViewHolder(private val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: Meal) {
            Glide.with(context)
                .load(meal.strMealThumb)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.imgMeal)
            binding.tvMealName.text = meal.strMeal

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
        val meal = getItem(position)
        meal?.let {
            holder.bind(it)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(meal: Meal)
    }
}
