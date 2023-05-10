package com.example.androidtraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidtraining.ui.theme.AndroidTrainingTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTrainingTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
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
                                HomeScreen(navigateToProductScreen = { navController.navigate("ProductScreen") })
//                                ProductScreen()
                            }
                            composable(route = "ProductScreen") {
                                ProductScreen()
                            }
                        }
                    }
//                    Greeting("Android")
//                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigateToProductScreen: () -> Unit = {}) {
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var passwordVisible = remember { mutableStateOf(false) }
    var errorVisible = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .size(200.dp)
            )
        }
        Text(text = "Log in", fontWeight = FontWeight.Bold, fontSize = 30.sp)
        OutlinedTextField(
            value = email.value,
            onValueChange = { newValue ->
                email.value = newValue
            },
            isError = !isValidEmail(email.value),
            label = { Text("Email") },
            placeholder = { Text("Input email") },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            singleLine = true,
            placeholder = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth(),
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible.value)
                    painterResource(R.drawable.visibility)
                else painterResource(R.drawable.visibility_off)

                val description = if (passwordVisible.value) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Image(painter = image, description)
                }
            })
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp),
            onClick = {
                if (checkForValidCredentials(
                        email.value,
                        password.value
                    )
                ) navigateToProductScreen()
                else errorVisible.value = true
            }
        ) {
            Text(text = "Log in")
        }
        Text(text = if (errorVisible.value) "Invalid credentials" else "")
    }
}

fun checkForValidCredentials(email: String, password: String): Boolean {
    var validEmail = "marti@gmail.com"
    var validPassword = "Pass123"
    if (email == validEmail && password == validPassword) {
        return true
    }
    return false
}

@Composable
fun ProductScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
            Text(text = "Item")
            Icon(Icons.Default.ShoppingCart, contentDescription = null)

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(painter = painterResource(id = R.drawable.photo), contentDescription = "tea pot")
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "My Tea Pot", fontWeight = FontWeight.Bold)
            Text(text = "5 stars")
        }
        Text(text = "Category: Home")
        Text(text = "a container of earthenware, metal, etc., usually round and deep and having a handle or handles and often a lid, used for cooking, serving, and other purposes")

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            text = "$90.00",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            onClick = { /*TODO*/ }) {
            Icon(Icons.Default.Add, contentDescription = null)
            Text(text = "Add to cart")
        }
    }
}

fun isValidEmail(email: String): Boolean {
    if (email.isNotEmpty()) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    return true
}
