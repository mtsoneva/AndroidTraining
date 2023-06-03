package com.example.androidtraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidtraining.screens.login.LoginScreen
import com.example.androidtraining.screens.product.ProductScreen
import com.example.androidtraining.ui.theme.AndroidTrainingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTrainingTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold {
                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            modifier = Modifier.padding(it)
                        ) {
                            composable(route = "home") {
                                LoginScreen(navigateToProductScreen = { navController.navigate("ProductScreen") })
                            }
                            composable(route = "ProductScreen") {
                                ProductScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}