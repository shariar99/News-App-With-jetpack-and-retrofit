package com.shariar99.newsapps

data class NewsData(val id:Int,
                    val image: Int=R.drawable.braking,
                    val author: String,
                    val title:String,
                    val description:String,
                    val publishedAt:String,)