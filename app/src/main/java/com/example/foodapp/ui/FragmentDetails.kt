package com.example.foodapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.apiMinuman.DrinkViewModel
import com.example.foodapp.databinding.FragmentDetailsBinding
import com.example.foodapp.foodBookmark.BookmarkViewModel
import com.example.foodapp.meal.MealViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentDetails : Fragment(R.layout.fragment_details) {
    lateinit var binding: FragmentDetailsBinding
    private val viewModel by viewModels<MealViewModel>()
    private val bookViewModel by viewModels<BookmarkViewModel>()
    private val drinkViewModel by viewModels<DrinkViewModel>()
    private val args by navArgs<FragmentDetailsArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        binding.imgToolbarBtnBack.setOnClickListener() {
            findNavController().navigate(R.id.action_navigation_details_to_navigation_home)
        }

        viewModel.detailMeal(args.meal!!.idMeal).observe(viewLifecycleOwner) {
            Glide.with(requireContext())
                .load(args.meal!!.strMealThumb)
                .into(binding.imgItem)
            var ingre = args.meal
            binding.tvCategory.text = args.meal!!.strCategory
            binding.tvIngredients.text =
                "${ingre!!.strIngredient1}+${ingre.strIngredient2}+${ingre.strIngredient3}"
            binding.tvInstructions.text = args.meal!!.strInstructions

            binding.btnYoutube.setOnClickListener {
                val youtubeUrl = args.meal!!.strYoutube

                // Periksa apakah URL YouTube tidak kosong
                if (!youtubeUrl.isNullOrBlank()) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))

                    if (intent.resolveActivity(requireActivity().packageManager) != null) {
                        startActivity(intent)
                    } else {
                        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
                        startActivity(webIntent)
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "URL YouTube tidak tersedia",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        drinkViewModel.detailDrink(args.drink!!.idDrink).observe(viewLifecycleOwner) {
            Glide.with(requireContext())
                .load(args.drink!!.strDrinkThumb)
                .into(binding.imgItem)
            var ingre = args.drink
            binding.tvCategory.text = args.drink!!.strCategory
            binding.tvIngredients.text =
                "${ingre!!.strIngredient1}+${ingre.strIngredient2}+${ingre.strIngredient3}"
            binding.tvInstructions.text = args.drink!!.strInstructions
        }

        var isChecked=false
        CoroutineScope(Dispatchers.IO).launch {
            var count=bookViewModel.getBookmarkId(args.meal!!.idMeal)
            with(Dispatchers.Main){
                if (count==null){
                    isChecked=false
                    binding.toggleFavorite.isChecked=false
                }
                else{
                    isChecked=true
                    binding.toggleFavorite.isChecked=true
                }
            }
        }
        binding.toggleFavorite.setOnClickListener(){
            if (isChecked==false){
                binding.toggleFavorite.isChecked=true
                bookViewModel.addToBookmark(args.meal!!)
            }
            else{
                binding.toggleFavorite.isChecked=false
                bookViewModel.deleteBookmark(args.meal!!.idMeal)
            }
        }
    }
}