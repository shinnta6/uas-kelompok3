package com.example.foodapp.di
import android.content.Context
import androidx.room.Room
import com.example.foodapp.api.MealApi
import com.example.foodapp.apiMinuman.DrinkApi
import com.example.foodapp.foodBookmark.BookmarkDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    @MealRetrofit
    fun provideMealRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(MealApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    @DrinkRetrofit  // Add this annotation to specify the qualifier
    fun provideDrinkRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(DrinkApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideMealApi(@MealRetrofit retrofit: Retrofit): MealApi = retrofit.create(MealApi::class.java)

    @Singleton
    @Provides
    fun provideDrinkApi(@DrinkRetrofit retrofit: Retrofit): DrinkApi = retrofit.create(DrinkApi::class.java)

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class MealRetrofit

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class DrinkRetrofit

    @Singleton
    @Provides
    fun providesBookmark(@ApplicationContext app: Context):BookmarkDB= Room.databaseBuilder(
        app,BookmarkDB::class.java,"favMealsss").build()

    @Singleton
    @Provides
    fun providesBookmarkDao(db: BookmarkDB)=db.bookmarkDao()
}
