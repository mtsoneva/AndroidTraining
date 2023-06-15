package com.example.androidtraining.ui.login

import android.widget.Toast
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidtraining.R
import com.example.androidtraining.models.UserInfo
import com.example.androidtraining.ui.theme.Purple

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navigateToProductScreen: () -> Unit = {}) {
    val viewModel: LoginViewModel = hiltViewModel()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val userInfo = UserInfo(email.value, password.value)
    val isLoading by viewModel.isLoading.collectAsState()
    val successfulLogin by viewModel.successfulLogin.collectAsState()

    LaunchedEffect(successfulLogin) {
        successfulLogin?.let {
            if (it) navigateToProductScreen() else Toast.makeText(
                context,
                "Wrong credentials",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    val isValidEmail: () -> Boolean = {
        if (email.value.isNotEmpty()) {
            android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches();
        } else {
            true
        }
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.login_background),
                contentScale = ContentScale.FillWidth
            )
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
        Text(text = "Log in", fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Purple)
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
                .padding(vertical = 35.dp)
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

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 35.dp, horizontal = 10.dp),
            onClick = {
                keyboardController?.hide()
                viewModel.login(userInfo)
            },
            colors = ButtonDefaults.buttonColors(Purple),
            enabled = !isLoading
        ) {
            Text(text = "Log in")
        }
    }
}