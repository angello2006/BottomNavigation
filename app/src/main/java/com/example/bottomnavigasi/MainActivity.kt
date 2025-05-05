package com.example.bottomnavigasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigasi.ui.theme.BottomNavigasiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           BottomNavigasiTheme {
                Surface (modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background){
                    AppScreen()
                }
            }
        }
    }
}



@Composable
fun BottomNavigationBar(navController: NavController, appItems: List<Destination> ) {
    BottomNavigasiTheme {
        BottomNavigation (
            backgroundColor = colorResource(id = R.color.purple_700),
            contentColor = Color.White,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            appItems.forEach { item -> BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon),
                    contentDescription = item.title)},
                label = { Text(text = item.title)},
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute ==item.route,
                onClick = {
                    navController.navigate(item.route){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                            saveState = true
                        }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            ) }
        }

    }
}

@Composable
fun AppNavigation(navController : NavHostController){
    NavHost(navController, startDestination =
    Destination.Transactions.route) {
        composable(Destination.Transactions.route){
            Transactions()
        }
        composable (Destination.Budget.route){
            Budget()
        }
        composable (Destination.Tasks.route){
            Tasks()
        }
        composable (Destination.Settngs.route){
            Settings()
        }
    }
}
@Composable
fun AppScreen(){
    val navController = rememberNavController()
Scaffold(
bottomBar = {
    BottomNavigationBar(navController =
        navController, appItems = Destination.tolist)},
content = {padding ->
    Box(modifier = Modifier.padding(padding))
    {
        AppNavigation(navController = navController)
    }
}
)
}