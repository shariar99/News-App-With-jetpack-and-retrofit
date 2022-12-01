package com.shariar99.newsapps.ui.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import com.shariar99.newsapps.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shariar99.models.TopNewsArticle
import com.shariar99.newsapps.MockData
import com.shariar99.newsapps.MockData.getTimeAgo
import com.shariar99.newsapps.NewsData
import com.skydoves.landscapist.coil.CoilImage


@Composable
fun TopNews(navController: NavController,articles :List<TopNewsArticle>){
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        LazyColumn{
             items(articles.size){
                 index->
                 TopNewsItem(article = articles[index],
                 onNewsClick={navController.navigate("Detail/$index")})


             }

        }
    }
    
}

@Composable
fun TopNewsItem(article: TopNewsArticle, onNewsClick:() ->Unit ={}){
    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp)
        .clickable {
            onNewsClick()
        }){

        CoilImage(imageModel = article.urlToImage,
        contentScale = ContentScale.Crop,
        error = ImageBitmap.imageResource(R.drawable.braking),
        placeHolder =ImageBitmap.imageResource(R.drawable.braking)
        )


        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween)
        {
            Text(text = MockData.stringToDate(article.publishedAt!! ).getTimeAgo(),
                color =Color.White,
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(80.dp))
            Text(text = article.title!!,
                color =Color.White,
                fontWeight = FontWeight.SemiBold)

        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsPreView(){
    TopNewsItem(TopNewsArticle(

        author = " Aljajira",
        title = "BAngh KIng joe exoytisb  aiosb hoba ",
        description = "Aikhan a kisu hoba kintu likhar time amr nai karon apps develop korta hob a",
        publishedAt = "2022-11-27T04:42:40"
    ))
}