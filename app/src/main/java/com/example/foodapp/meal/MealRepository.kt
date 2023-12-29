package com.example.foodapp.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.foodapp.api.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepository @Inject constructor(private var mealApi: MealApi){

    fun searchMeal(query:String)=Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = {MealPagingSource(mealApi,query)}
    ).liveData

    fun detailMeal(i:String):LiveData<MealResponse>{
        var detail=MutableLiveData<MealResponse>()
        mealApi.detailMeal(i).enqueue(object :Callback<MealResponse>{
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                if (response.isSuccessful){
                    detail.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {

            }

        })
        return detail
    }

}