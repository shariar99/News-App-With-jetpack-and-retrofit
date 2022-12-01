package com.shariar99.network

import android.util.Log
import androidx.compose.runtime.*
import com.shariar99.models.ArticleCategory
import com.shariar99.models.TopNewsResponse
import com.shariar99.models.getArticleCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsManager {
    private  val _newsResponse = mutableStateOf(TopNewsResponse())
        val resResponse:State<TopNewsResponse>
    @Composable get() = remember {
        _newsResponse
    }
    private  val _getArticleByCategory = mutableStateOf(TopNewsResponse())
    val getArticleByCategory:MutableState<TopNewsResponse>
        @Composable get() = remember {
            _getArticleByCategory
        }

    val selectedCategory:MutableState<ArticleCategory?> = mutableStateOf(null)

    init {
        getArticle()
    }


    private  fun getArticle(){
        val service = Api.retrofitService.getTopArticles("us",Api.API_KEY)
        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful){
                    _newsResponse.value = response.body()!!
                    Log.d("news","${_newsResponse.value}")
                }else{
                    Log.d("error","${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error","${t.printStackTrace()}")
            }
        })
    }

    fun getArticleByCategory(category:String){
        val service = Api.retrofitService.getTopArticlesByCategory(category,Api.API_KEY)
        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful){
                    _getArticleByCategory.value = response.body()!!
                    Log.d("category","${_getArticleByCategory.value}")
                }else{
                    Log.d("error","${response.code()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error","${t.printStackTrace()}")
            }
        })

    }


    fun onSelectedCategoryChanged(category:String){
        val newsCategory = getArticleCategory(category= category)
        selectedCategory.value = newsCategory

    }

}