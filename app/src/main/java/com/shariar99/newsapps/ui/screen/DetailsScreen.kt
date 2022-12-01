package com.shariar99.newsapps.ui.screen


import android.annotation.SuppressLint
import android.telecom.Call.Details
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shariar99.models.TopNewsArticle
import com.shariar99.newsapps.MockData
import com.shariar99.newsapps.MockData.getTimeAgo
import com.shariar99.newsapps.NewsData
import com.shariar99.newsapps.R
import com.skydoves.landscapist.coil.CoilImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(article: TopNewsArticle, scrollState: ScrollState, navController: NavController){

    Scaffold(topBar = {
        DetailsScreenTopAppBar(onBackPress = {navController.popBackStack()})

    })
    {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally) {


            CoilImage(imageModel = article.urlToImage,
                contentScale = ContentScale.Crop,
                error = ImageBitmap.imageResource(R.drawable.braking),
                placeHolder = ImageBitmap.imageResource(R.drawable.braking)
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                InfoWithIcon(Icons.Default.Edit, info = article.author?:"Not Available" )
                InfoWithIcon(Icons.Default.DateRange,
                    info = MockData.stringToDate(article.publishedAt!!).getTimeAgo())

            }
            Text(text = article.title?:"Not Available", fontWeight = FontWeight.Bold)
            Text(text = article.description?:"Not Available", modifier = Modifier.padding(top = 16.dp))

        }
    }
}
@Composable
fun DetailsScreenTopAppBar(onBackPress: ()-> Unit={}){
    TopAppBar(title = { Text(text = "Details Screen", fontWeight = FontWeight.SemiBold)}

        , navigationIcon = {
            IconButton(onClick = {onBackPress()}){
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")

            }
        }
    )
}


@Composable
fun InfoWithIcon(icon: ImageVector ,info:String){

    Row {
       Icon(icon , contentDescription ="", modifier = Modifier.padding(end = 8.dp),
           colorResource(id = R.color.purple_500))
        Text(text = info)
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview(){
    DetailsScreen(
        TopNewsArticle(
            author = " Aljajira",
            title = "BAngh KIng joe exoytisb  aiosb hoba ",
            description = "Aikhan a kisu hoba kintu likhar time amr nai karon apps develop korta hob a",
            publishedAt = "2021-11-04T04:42:40Z"

        ),
        rememberScrollState(),
        rememberNavController()
    )

}