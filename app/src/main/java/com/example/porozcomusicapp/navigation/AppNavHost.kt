package com.example.porozcomusicapp.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.porozcomusicapp.screens.detail.DetailScreen
import com.example.porozcomusicapp.screens.home.HomeScreen
import androidx.navigation.toRoute
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable


@OptIn(InternalSerializationApi::class)

object Routes {
    @Serializable
    object Home

    @Serializable
    data class Detail(val albumId: String)
}



@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home,
        modifier = modifier
    ) {
        composable<Routes.Home> {
            HomeScreen(
                onAlbumClick = { albumId ->
                    navController.navigate(Routes.Detail(albumId = albumId))
                }
            )
        }

        composable<Routes.Detail> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.Detail>()
            DetailScreen(
                albumId = args.albumId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}