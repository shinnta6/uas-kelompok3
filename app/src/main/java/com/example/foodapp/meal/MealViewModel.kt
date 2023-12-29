package com.example.foodapp.meal

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.foodapp.api.MealResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(private var repository: MealRepository
,private var savedStateHandle: SavedStateHandle):ViewModel() {

    companion object{
        const val DEFAULT_QUERY="potato"
        const val CURRENT_QUERY="current"
    }
    private var currentQuery=savedStateHandle.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)
    var meal=currentQuery.switchMap { query->
       repository.searchMeal(query).cachedIn(viewModelScope)
    }

    fun detailMeal(i:String):LiveData<MealResponse>{
        return repository.detailMeal(i)
    }

}