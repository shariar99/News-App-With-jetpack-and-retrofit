package com.shariar99.newsapps.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shariar99.components.BottomMenu
import com.shariar99.models.TopNewsArticle
import com.shariar99.network.NewsManager
import com.shariar99.newsapps.BottomMenuScreen
import com.shariar99.newsapps.MockData
import com.shariar99.newsapps.ui.screen.Categories
import com.shariar99.newsapps.ui.screen.DetailsScreen
import com.shariar99.newsapps.ui.screen.Sources
import com.shariar99.newsapps.ui.screen.TopNews

@Composable
fun NewsApp(){
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
   MainScreen(navController=navController,scrollState)
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState){
    Scaffold(
        bottomBar = {
            BottomMenu(navController)
        },
    ) {
        Navigation(navController =navController , scrollState=scrollState, paddingValues = it)
        
    }
}


@Composable
fun Navigation(navController: NavHostController ,
               scrollState: ScrollState,
               newsManager: NewsManager = NewsManager(),
               paddingValues: PaddingValues
){

    val articles = newsManager.resResponse.value.articles
    Log.d("news","$articles")

    articles?.let {
        NavHost(navController = navController, startDestination =
        BottomMenuScreen.TopNews.route, modifier = Modifier.padding(paddingValues = paddingValues)
        ){
            bottomNavigation(navController= navController,articles,newsManager)

            composable("Detail/{index}",
                arguments = listOf(navArgument("index"){
                    type = NavType.IntType
                })
            ){
                    navBackStackEntry ->
                val index = navBackStackEntry.arguments?.getInt("index")
                index?.let {
                    val article = articles[index]
                    DetailsScreen(article,scrollState , navController)
                }



            }
    }


    }


}

fun NavGraphBuilder.bottomNavigation(navController: NavController,
                                     articles: List< TopNewsArticle>,
                                     newsManager: NewsManager){
    composable(BottomMenuScreen.TopNews.route){
        TopNews(navController = navController,articles)
    }
    composable(BottomMenuScreen.Categories.route){

        newsManager.getArticleByCategory("business")
        newsManager.onSelectedCategoryChanged("business")
       Categories(newsManager = newsManager,
       onFetchCategory = {
           newsManager.onSelectedCategoryChanged(it)
           newsManager.getArticleByCategory(it)
       })
    }
    composable(BottomMenuScreen.Sources.route){
        Sources()
    }
}