package com.example.foodapp.apiMinuman

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.foodapp.api.MealResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(private var repository: DrinkRepository
, private var savedStateHandle: SavedStateHandle):ViewModel() {

    companion object{
        const val DEFAULT_QUERY="margarita"
        const val CURRENT_QUERY="current"
    }
    private var currentQuery=savedStateHandle.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)
    var drink=currentQuery.switchMap { query->
        repository.searchDrink(query).cachedIn(viewModelScope)
    }

    fun detailDrink(i:String):LiveData<DrinkResponse>{
        return repository.detailDrink(i)
    }
}