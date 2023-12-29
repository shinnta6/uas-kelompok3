package com.example.foodapp.foodBookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.api.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(private var repository: BookmarkRepository):ViewModel() {
    fun addToBookmark(meal: Meal){
        CoroutineScope(Dispatchers.IO).launch {
            var book=Bookmark(
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
            repository.addToBookmark(book)
        }
    }
    fun getBookmark():LiveData<List<Bookmark>>{
        return repository.getBookmark()
    }
    suspend fun getBookmarkId(id:String):Bookmark?{
        return repository.getBookmarkId(id)
    }
    fun deleteBookmark(id:String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteBookmark(id)
        }
    }
}