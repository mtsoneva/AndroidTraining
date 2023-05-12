package com.example.androidtraining.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import com.example.androidtraining.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navigateToProductScreen: () -> Unit = {}) {
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var passwordVisible = remember { mutableStateOf(false) }
    var errorVisible = remember { mutableStateOf(false) }
    val checkForValidCredentials: () -> Boolean = {
        email.value == "marti@gmail.com" && password.value == "Pass123"
    }
    val isValidEmail: () -> Boolean = {
        if (email.value.isNotEmpty()) {
            android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches();
        } else {
            true
        }
    }
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
            isError = !isValidEmail(),
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
        if (errorVisible.value) {
            Text(text = "Invalid credentials")
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp),
            onClick = {
                if (checkForValidCredentials()
                ) navigateToProductScreen()
                else errorVisible.value = true
            }
        ) {
            Text(text = "Log in")
        }
    }
}