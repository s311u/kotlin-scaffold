package com.example.scaffoldtest

import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffoldtest.ui.theme.ScaffoldTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldApp()
                }
            }
        }
    }
}
@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable(route = "Home") {
            MainScreen(navController)
        }
        composable(route = "Info"){
            InfoScreen(navController)
        }
        composable(route = "Settings") {
            SettingsScreen(navController)
        }
    }
}

@Composable
fun MainScreen(navController: NavController){
    Scaffold(
        topBar = { MainTopBar("my app", navController)},
        content = { Text(text= "Content for home screen")},
        //bottomBar = { BottomAppBar { Text(text = "Bottom bar")}}
    )
}
@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("info", navController)},
        content = { Text(text= "content for info screen")}
    )
}

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Settings", navController = navController)},
        content = { Text(text = "content for settings screen")}
    )
}

@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false })
            {
                DropdownMenuItem(onClick = { navController.navigate("info") }) {
                    Text("Info")
                }
                DropdownMenuItem(onClick = { navController.navigate("settings") }) {
                    Text("Settings")
                }
            }
        }
    )
}

@Composable
fun ScreenTopBar(title:String, navController: NavController){
    TopAppBar(
        title = { Text(title)},
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}


