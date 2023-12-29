package com.example.foodapp.foodBookmark

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.ItemMealBinding


class BookmarkAdapter(private val context: Context, private val dataSet: ArrayList<Bookmark>,
private var listener: onClickListener) :
    RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class ViewHolder(var binding:ItemMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bookmark: Bookmark) {
            Glide.with(context)
                .load(bookmark.strMealThumb)
                .into(binding.imgMeal)
            binding.tvMealName.text=bookmark.strMeal

            binding.root.setOnClickListener(){
                var position=bindingAdapterPosition
                if (position!=RecyclerView.NO_POSITION){
                    var item=dataSet[position]
                    listener.detail(item)
                }
            }
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = ItemMealBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.textView.text = dataSet[position]
        var bookmark=dataSet[position]
        viewHolder.bind(bookmark)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    interface onClickListener{
        fun detail(bookmark: Bookmark)
    }

    fun setList(newList:List<Bookmark>){
        dataSet.addAll(newList)
        notifyDataSetChanged()
    }

}
