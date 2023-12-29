package com.example.foodapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.api.Meal
import com.example.foodapp.databinding.FragmentBookmarkBinding
import com.example.foodapp.foodBookmark.Bookmark
import com.example.foodapp.foodBookmark.BookmarkAdapter
import com.example.foodapp.foodBookmark.BookmarkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentBookmark:Fragment(R.layout.fragment_bookmark) {
    lateinit var binding: FragmentBookmarkBinding
    private val viewModel by viewModels<BookmarkViewModel>()
    lateinit var bookmarkAdapter: BookmarkAdapter
    private var bookmarkList= arrayListOf<Bookmark>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentBookmarkBinding.bind(view)

        bookmarkAdapter= BookmarkAdapter(requireContext(),bookmarkList,object :BookmarkAdapter.onClickListener{
            override fun detail(bookmark: Bookmark) {
                var bookmarker = Meal(
                    bookmark.dateModified ?: "", // Handle null dateModified
                    bookmark.idMeal,
                    bookmark.strArea,
                    bookmark.strCategory,
                    // Handle null strCreativeCommonsConfirmed
                    bookmark.strDrinkAlternate ?: "", // Handle null strDrinkAlternate
                    bookmark.strImageSource ?: "", // Handle null strImageSource
                    bookmark.strIngredient1 ?: "", // Handle null strIngredient1
                    bookmark.strIngredient10 ?: "", // Handle null strIngredient10
                    bookmark.strIngredient11 ?: "", // Handle null strIngredient11
                    bookmark.strIngredient12 ?: "", // Handle null strIngredient12
                    bookmark.strIngredient13 ?: "", // Handle null strIngredient13
                    bookmark.strIngredient14 ?: "", // Handle null strIngredient14
                    bookmark.strIngredient15 ?: "", // Handle null strIngredient15
                    bookmark.strIngredient16 ?: "", // Handle null strIngredient16
                    bookmark.strIngredient17 ?: "", // Handle null strIngredient17
                    bookmark.strIngredient18 ?: "", // Handle null strIngredient18
                    bookmark.strIngredient19 ?: "", // Handle null strIngredient19
                    bookmark.strIngredient2 ?: "", // Handle null strIngredient2
                    bookmark.strIngredient20 ?: "", // Handle null strIngredient20
                    bookmark.strIngredient3 ?: "", // Handle null strIngredient3
                    bookmark.strIngredient4 ?: "", // Handle null strIngredient4
                    bookmark.strIngredient5 ?: "", // Handle null strIngredient5
                    bookmark.strIngredient6 ?: "", // Handle null strIngredient6
                    bookmark.strIngredient7 ?: "", // Handle null strIngredient7
                    bookmark.strIngredient8 ?: "", // Handle null strIngredient8
                    bookmark.strIngredient9 ?: "", // Handle null strIngredient9
                    bookmark.strInstructions ?: "", // Handle null strInstructions
                    bookmark.strMeal,
                    bookmark.strMealThumb ?: "", // Handle null strbookmarkThumb
                    bookmark.strMeasure1 ?: "", // Handle null strMeasure1
                    bookmark.strMeasure10 ?: "", // Handle null strMeasure10
                    bookmark.strMeasure11 ?: "", // Handle null strMeasure11
                    bookmark.strMeasure12 ?: "", // Handle null strMeasure12
                    bookmark.strMeasure13 ?: "", // Handle null strMeasure13
                    bookmark.strMeasure14 ?: "", // Handle null strMeasure14
                    bookmark.strMeasure15 ?: "", // Handle null strMeasure15
                    bookmark.strMeasure16 ?: "", // Handle null strMeasure16
                    bookmark.strMeasure17 ?: "", // Handle null strMeasure17
                    bookmark.strMeasure18 ?: "", // Handle null strMeasure18
                    bookmark.strMeasure19 ?: "", // Handle null strMeasure19
                    bookmark.strMeasure2 ?: "", // Handle null strMeasure2
                    bookmark.strMeasure20 ?: "", // Handle null strMeasure20
                    bookmark.strMeasure3 ?: "", // Handle null strMeasure3
                    bookmark.strMeasure4 ?: "", // Handle null strMeasure4
                    bookmark.strMeasure5 ?: "", // Handle null strMeasure5
                    bookmark.strMeasure6 ?: "", // Handle null strMeasure6
                    bookmark.strMeasure7 ?: "", // Handle null strMeasure7
                    bookmark.strMeasure8 ?: "", // Handle null strMeasure8
                    bookmark.strMeasure9 ?: "", // Handle null strMeasure9
                    bookmark.strSource ?: "", // Handle null strSource
                    bookmark.strTags ?: "", // Handle null strTags
                    bookmark.strYoutube ?: "" // Handle null strYoutube
                )
                var action =FragmentBookmarkDirections.actionNavigationBookmarkToNavigationDetails(null, null)
                findNavController().navigate(action)
            }

        })
        binding.favRecView.layoutManager=LinearLayoutManager(requireContext())
        binding.favRecView.adapter=bookmarkAdapter

        viewModel.getBookmark().observe(viewLifecycleOwner){
            bookmarkAdapter.setList(it)
        }
    }
}