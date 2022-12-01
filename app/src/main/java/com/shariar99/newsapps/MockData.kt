package com.shariar99.newsapps

import android.os.Build
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object MockData {
    val topNewsList = listOf<NewsData>(

        NewsData(
             1,
            R.drawable.ataonew,
            author = " RAZEK CNN",
            title = "Tiger KIng joe exoytisb  aiosb hoba ",
            description = "Aikhan a kisu hoba kintu likhar time amr nai karon apps develop korta hob a",
            publishedAt = "2021-11-04T04:42:40Z"
        ),
        NewsData(
             2,
            R.drawable.barfrance,
            author = " Aljajira",
            title = "BAngh KIng joe exoytisb  aiosb hoba ",
            description = "Aikhan a kisu hoba kintu likhar time amr nai karon apps develop korta hob a",
            publishedAt = "2021-11-04T04:42:40Z"
        ),
        NewsData(
        3,
            R.drawable.bergar,
            author = "kida jani  RAZEK CNN",
            title = "laion KIng joe exoytisb  aiosb hoba ",
            description = "Aikhan a kisu hoba kintu likhar time amr nai karon apps develop korta hob a",
            publishedAt = "2021-11-04T04:42:40Z"
        ),
        NewsData(
          4,
            R.drawable.braking,
            author = "ata p  CNN",
            title = "Hati joe exoytisb  aiosb hoba ",
            description = "Aikhan a kisu hoba kintu likhar time amr nai karon apps develop korta hob a",
            publishedAt = "2021-11-04T04:42:40Z"
        ),
        NewsData(
          5,
            R.drawable.desert,
            author = "RAJA RAZEK CNN",
            title = "T exoytisb  aiosb hoba ",
            description = "Aikhan a kisu hoba kintu likhar time amr nai karon apps develop korta hob a",
            publishedAt = "2021-11-04T04:42:40Z"
        ),
        NewsData(
             6,
            R.drawable.france,
            author = "RAJA RAZEK CNN",
            title = "Tfdf oytisb  aiosb hoba ",
            description = "Aikhan a kisu hoba kintu likhar time amr nai karon apps develop korta hob a",
            publishedAt = "2021-11-04T04:42:40Z"
        ),
        NewsData(
            7,
            R.drawable.bergar,
            author = "RAJA RAZEK CNN",
            title = "gfh voytisb  aiosb hoba ",
            description = "Aikhan a kisu hoba kintu likhar time amr nai karon apps develop korta hob a",
            publishedAt = "2021-11-04T04:42:40Z"
        ),
        NewsData(
          8,
            R.drawable.barfrance,
            author = "RAJA RAZEK CNN",
            title = "Kandk joe exoytisb  aiosb hoba ",
            description = "Aikhan a kisu hoba kintu likhar time amr nai karon apps develop korta hob a",
            publishedAt = "2021-11-04T04:42:40Z"
        ),
    )

    fun getNews(newsId: Int?):NewsData{
        return topNewsList.first{it.id==newsId}
    }

    fun Date.getTimeAgo():String{
        val calendar = Calendar.getInstance()
        calendar.time = this

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val currentCalendar = Calendar.getInstance()

        val currentYear = currentCalendar.get(Calendar.YEAR)
        val currentMonth = currentCalendar.get(Calendar.MONTH)
        val currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH)
        val currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = currentCalendar.get(Calendar.MINUTE)

        return if (year < currentYear){
            val interval = currentYear - year
            if (interval==1 )"$interval year ago " else "$interval Years ago"
        }else if (month < currentMonth){
            val interval = currentMonth - month
            if (interval==1 )"$interval Month ago " else "$interval Months ago"
        }else if (day < currentDay){
            val interval = currentDay - day
            if (interval==1 )"$interval Day ago " else "$interval Days ago"
        }else if (hour < currentHour){
            val interval = currentHour - hour
            if (interval==1 )"$interval Hour ago " else "$interval Hours ago"
        }
        else if (minute < currentMinute){
            val interval = currentMinute - minute
            if (interval==1 )"$interval Minute ago " else "$interval Minutes ago"
        }else{
            "A moment ago"
        }

    }
    fun stringToDate(publishedAT:String):Date{
        val date =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
                Locale.ENGLISH).parse(publishedAT)
            }else{
                java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
                    Locale.ENGLISH).parse(publishedAT)
            }
        Log.d("published","$date")
        return date
    }


}