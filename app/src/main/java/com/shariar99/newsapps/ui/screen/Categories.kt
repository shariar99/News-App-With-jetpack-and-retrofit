package com.shariar99.newsapps.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.shariar99.models.TopNewsArticle
import com.shariar99.models.getAllArticleCategory
import com.shariar99.network.NewsManager
import com.shariar99.newsapps.MockData
import com.shariar99.newsapps.MockData.getTimeAgo
import com.shariar99.newsapps.R
import com.skydoves.landscapist.coil.CoilImage


@Composable
fun Categories(onFetchCategory: (String) -> Unit={}, newsManager: NewsManager){

    val tapsItems = getAllArticleCategory()
    Column{
        LazyRow{
            items(tapsItems.size){
                val category =tapsItems[it]
                CategoryTab(category = category.categoryName,
                    onFetchCategory =onFetchCategory,
                    isSelected = newsManager.selectedCategory.value == category

                )
            }
        }
        ArticleContent(articles = newsManager.getArticleByCategory.value.articles?: listOf())
    }
}
@Composable
fun CategoryTab(category :String,isSelected:Boolean= false,
                onFetchCategory: (String)->Unit){
    val background = if (isSelected) colorResource(id = R.color.purple_200)
    else colorResource(id = R.color.purple_700)

    Surface(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 16.dp)
            .clickable {
                onFetchCategory(category)
            },
        shape = MaterialTheme.shapes.small,
        color = background
    ){
        Text(text = category,
        style = MaterialTheme.typography.body2,
        color= Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }

}

@Composable
fun ArticleContent(articles:List<TopNewsArticle>,modifier: Modifier =Modifier){
    LazyColumn{
        items(articles){
            article->
            Card(modifier.padding(8.dp),
                border= BorderStroke(2.dp, color = colorResource(id = R.color.purple_500)))
            {
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                    CoilImage(imageModel = article.urlToImage,
                    modifier = Modifier.size(100.dp),
                    placeHolder = painterResource(id = R.drawable.braking),
                    error = painterResource(id = R.drawable.braking))

                    Column(modifier.padding(8.dp)){
                        Text(text = article.title ?: "Not Available",
                        fontWeight = FontWeight.Bold,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween)
                        {
                            Text(text = article.author?: "Not Available")
                            Text(text = MockData.stringToDate(
                                article.publishedAt?:"2021-11-1-T14:25:20Z").getTimeAgo())

                        }
                    }

                }


            }
        }
    }
}