package com.example.foodapp.apiMinuman

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DrinkRepository @Inject constructor(private var drinkApi: DrinkApi) {
    fun searchDrink(query:String)=Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { DrinkPagingSource(drinkApi,query) }
    ).liveData

    fun detailDrink(i:String):LiveData<DrinkResponse>{
        var detail=MutableLiveData<DrinkResponse>()
        drinkApi.detailDrink(i).enqueue(object :Callback<DrinkResponse>{
            override fun onResponse(call: Call<DrinkResponse>, response: Response<DrinkResponse>) {
                if (response.isSuccessful){
                    detail.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<DrinkResponse>, t: Throwable) {

            }

        })
        return detail
    }
}