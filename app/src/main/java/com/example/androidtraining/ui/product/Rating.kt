package com.example.androidtraining.ui.product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidtraining.ui.theme.GreyStar
import com.example.androidtraining.ui.theme.PurpleStar
import com.example.androidtraining.utils.Constants

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Rating(rating: Int) {
    Box() {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "$rating")
            repeat(rating) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier
                        .size(14.dp),
                    tint = PurpleStar
                )
            }
            repeat(Constants.MAX_RATING - rating) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier
                        .size(14.dp),
                    tint = GreyStar
                )
            }
        }
    }
}