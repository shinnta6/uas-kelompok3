package com.example.foodapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.api.Meal
import com.example.foodapp.apiMinuman.Drink
import com.example.foodapp.apiMinuman.DrinkAdapter
import com.example.foodapp.apiMinuman.DrinkResponse
import com.example.foodapp.apiMinuman.DrinkViewModel
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.meal.MealAdapter
import com.example.foodapp.meal.MealViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHome : Fragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModels<MealViewModel>()
    lateinit var mealAdapter: MealAdapter
    private var mealList = arrayListOf<Meal>()

    private var drinkList = arrayListOf<Drink>()
    private val drinkViewModel by viewModels<DrinkViewModel>()
    lateinit var drinkAdapter: DrinkAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.buttonMinuman.setOnClickListener(){
            drinkAdapter = DrinkAdapter(requireContext(), object : DrinkAdapter.OnItemClickListener {
                override fun onItemClick(drink: Drink) {
                    // Handle item click here
                    var drinker = Drink(
                        drink.dateModified ?: "", // Handle null dateModified
                        drink.idDrink,
                        drink.strAlcoholic,
                        drink.strCategory,
                        // Handle null strCreativeCommonsConfirmed
                        drink.strCreativeCommonsConfirmed ?: "", // Handle null strDrinkAlternate
                        drink.strDrink ?: "", // Handle null strImageSource
                        drink.strDrinkAlternate ?: "", // Handle null strIngredient1
                        drink.strDrinkThumb ?: "", // Handle null strIngredient10
                        drink.strGlass ?: "", // Handle null strIngredient11
                        drink.strIBA ?: "", // Handle null strIngredient12
                        drink.strImageAttribution ?: "", // Handle null strIngredient13
                        drink.strImageSource ?: "",
                        drink.strIngredient1 ?: "",
                        drink.strIngredient10 ?: "",
                        drink.strIngredient11 ?: "",
                        drink.strIngredient12 ?: "",
                        drink.strIngredient13 ?: "",
                        drink.strIngredient14 ?: "",
                        drink.strIngredient15 ?: "",
                        drink.strIngredient2 ?: "",
                        drink.strIngredient3 ?: "",
                        drink.strIngredient4 ?: "",
                        drink.strIngredient5 ?: "",
                        drink.strIngredient6 ?: "",
                        drink.strIngredient7 ?: "",
                        drink.strIngredient8 ?: "",
                        drink.strIngredient9 ?: "",
                        drink.strInstructions ?: "",
                        drink.strInstructionsDE ?: "",
                        drink.strInstructionsES ?: "",
                        drink.strInstructionsFR ?: "",
                        drink.strInstructionsIT ?: "",
                        drink.strMeasure1 ?: "",
                        drink.strMeasure10 ?: "",
                        drink.strMeasure11 ?: "",
                        drink.strMeasure12 ?: "",
                        drink.strMeasure13 ?: "",
                        drink.strMeasure14 ?: "",
                        drink.strMeasure15 ?: "",
                        drink.strMeasure2 ?: "",
                        drink.strMeasure3 ?: "",
                        drink.strMeasure4 ?: "",
                        drink.strMeasure5 ?: "",
                        drink.strMeasure6 ?: "",
                        drink.strMeasure7 ?: "",
                        drink.strMeasure8 ?: "",
                        drink.strMeasure9 ?: "",
                        drink.strTags ?: "",
                        drink.strVideo ?: "",// Handle null strTags
                    )
                    var meal = Meal(
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )

                    var action = FragmentHomeDirections.actionNavigationHomeToNavigationDetails(meal, drinker)
                    findNavController().navigate(action)
                }
            })

            binding.rvRandomMeal.layoutManager=LinearLayoutManager(requireContext())
            binding.rvRandomMeal.adapter=drinkAdapter

            drinkViewModel.drink.observe(viewLifecycleOwner, Observer {
                drinkAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            })
        }

        binding.buttonMakanan.setOnClickListener(){
            mealAdapter = MealAdapter(requireContext(), object : MealAdapter.OnItemClickListener {
                override fun onItemClick(meal: Meal) {
                    // Handle item click here
                    var mealer = Meal(
                        meal.dateModified ?: "", // Handle null dateModified
                        meal.idMeal,
                        meal.strArea,
                        meal.strCategory,
                        // Handle null strCreativeCommonsConfirmed
                        meal.strDrinkAlternate ?: "", // Handle null strDrinkAlternate
                        meal.strImageSource ?: "", // Handle null strImageSource
                        meal.strIngredient1 ?: "", // Handle null strIngredient1
                        meal.strIngredient10 ?: "", // Handle null strIngredient10
                        meal.strIngredient11 ?: "", // Handle null strIngredient11
                        meal.strIngredient12 ?: "", // Handle null strIngredient12
                        meal.strIngredient13 ?: "", // Handle null strIngredient13
                        meal.strIngredient14 ?: "", // Handle null strIngredient14
                        meal.strIngredient15 ?: "", // Handle null strIngredient15
                        meal.strIngredient16 ?: "", // Handle null strIngredient16
                        meal.strIngredient17 ?: "", // Handle null strIngredient17
                        meal.strIngredient18 ?: "", // Handle null strIngredient18
                        meal.strIngredient19 ?: "", // Handle null strIngredient19
                        meal.strIngredient2 ?: "", // Handle null strIngredient2
                        meal.strIngredient20 ?: "", // Handle null strIngredient20
                        meal.strIngredient3 ?: "", // Handle null strIngredient3
                        meal.strIngredient4 ?: "", // Handle null strIngredient4
                        meal.strIngredient5 ?: "", // Handle null strIngredient5
                        meal.strIngredient6 ?: "", // Handle null strIngredient6
                        meal.strIngredient7 ?: "", // Handle null strIngredient7
                        meal.strIngredient8 ?: "", // Handle null strIngredient8
                        meal.strIngredient9 ?: "", // Handle null strIngredient9
                        meal.strInstructions ?: "", // Handle null strInstructions
                        meal.strMeal,
                        meal.strMealThumb ?: "", // Handle null strMealThumb
                        meal.strMeasure1 ?: "", // Handle null strMeasure1
                        meal.strMeasure10 ?: "", // Handle null strMeasure10
                        meal.strMeasure11 ?: "", // Handle null strMeasure11
                        meal.strMeasure12 ?: "", // Handle null strMeasure12
                        meal.strMeasure13 ?: "", // Handle null strMeasure13
                        meal.strMeasure14 ?: "", // Handle null strMeasure14
                        meal.strMeasure15 ?: "", // Handle null strMeasure15
                        meal.strMeasure16 ?: "", // Handle null strMeasure16
                        meal.strMeasure17 ?: "", // Handle null strMeasure17
                        meal.strMeasure18 ?: "", // Handle null strMeasure18
                        meal.strMeasure19 ?: "", // Handle null strMeasure19
                        meal.strMeasure2 ?: "", // Handle null strMeasure2
                        meal.strMeasure20 ?: "", // Handle null strMeasure20
                        meal.strMeasure3 ?: "", // Handle null strMeasure3
                        meal.strMeasure4 ?: "", // Handle null strMeasure4
                        meal.strMeasure5 ?: "", // Handle null strMeasure5
                        meal.strMeasure6 ?: "", // Handle null strMeasure6
                        meal.strMeasure7 ?: "", // Handle null strMeasure7
                        meal.strMeasure8 ?: "", // Handle null strMeasure8
                        meal.strMeasure9 ?: "", // Handle null strMeasure9
                        meal.strSource ?: "", // Handle null strSource
                        meal.strTags ?: "", // Handle null strTags
                        meal.strYoutube ?: "" // Handle null strYoutube
                    )
                    var drink = Drink(
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )

                    var action = FragmentHomeDirections.actionNavigationHomeToNavigationDetails(mealer, drink)
                    findNavController().navigate(action)
                }
            })

            binding.rvRandomMeal.layoutManager=LinearLayoutManager(requireContext())
            binding.rvRandomMeal.adapter=mealAdapter

            viewModel.meal.observe(viewLifecycleOwner, Observer {
                mealAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            })
        }
    }
}
