package com.example.androidtraining.screens.product

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidtraining.R

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
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            painter = painterResource(id = R.drawable.photo),
            contentDescription = "tea pot"
        )

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