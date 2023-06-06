package com.example.androidtraining.screens.product

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.androidtraining.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProductScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val response by viewModel.productResponse.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.getProduct()
    }

    LaunchedEffect(response) {
        response?.let { result ->
            result.onFailure {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    viewModel.productResponse.value?.getOrNull()?.let { item ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
                Text(text = "Item")
                Icon(Icons.Default.ShoppingCart, contentDescription = null)

            }
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                painter = rememberAsyncImagePainter(item.image),
                contentDescription = item.title
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = item.title, fontWeight = FontWeight.Bold)
                Text(text = "${item.rating} stars")
            }
            Text(text = "Category: ${item.category}")
            Text(text = item.description)

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                text = item.price.toString(),
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
}