package com.example.androidtraining.ui.product

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.androidtraining.R
import com.example.androidtraining.ui.theme.Green
import com.example.androidtraining.ui.theme.GreyText
import com.example.androidtraining.ui.theme.Purple
import com.example.androidtraining.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProductScreen() {
    val viewModel: ProductViewModel = hiltViewModel()
    val context = LocalContext.current
    val successfulProductLoading by viewModel.successfulProductLoading.collectAsState()

    LaunchedEffect(true) {
        viewModel.getProduct()
    }

    LaunchedEffect(successfulProductLoading) {
        successfulProductLoading?.let {
            if (!it) Toast.makeText(context, R.string.log_in, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.item),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = stringResource(id = R.string.arrow_back)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        BadgedBox(
                            badge = {
                                Badge(
                                    contentColor = White,
                                    containerColor = Purple,
                                ) {
                                    val badgeNumber = "8"
                                    Text(
                                        badgeNumber,
                                        color = White
                                    )
                                }
                            }) {
                            Icon(
                                Icons.Outlined.ShoppingCart,
                                contentDescription = stringResource(id = R.string.shopping_cart)
                            )
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            viewModel.productResponse.value?.getOrNull()?.let { item ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .paint(
                            painterResource(id = R.drawable.product_background),
                            contentScale = ContentScale.FillWidth
                        )
                        .padding(horizontal = 30.dp)
                        .padding(top = paddingValues.calculateTopPadding())
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Image(
                                modifier = Modifier,
                                painter = rememberAsyncImagePainter(item.image),
                                contentDescription = item.title
                            )
                        }

                        Row(
                            modifier = Modifier
                                .padding(15.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(30.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(color = Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(horizontal = 15.dp)
                                ) {
                                    if (item.stock > 0) {
                                        Icon(
                                            Icons.Outlined.CheckCircle,
                                            contentDescription = null,
                                            tint = Green
                                        )
                                        Text(
                                            text = stringResource(id = R.string.in_stock),
                                            modifier = Modifier
                                                .padding(horizontal = 5.dp)
                                        )
                                    } else {
                                        Icon(
                                            Icons.Outlined.Clear,
                                            contentDescription = null,
                                            tint = Color.Red
                                        )
                                        Text(
                                            text = stringResource(id = R.string.out_of_stock),
                                            modifier = Modifier
                                                .padding(horizontal = 5.dp),
                                            color = Color.Red
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = item.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Rating(rating = item.rating)
                    }
                    Text(
                        text = stringResource(id = R.string.category, item.category),
                        fontSize = 12.sp,
                        color = GreyText
                    )

                    Text(
                        text = item.description,
                        Modifier.padding(vertical = 30.dp),
                        fontSize = 16.sp
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "$${String.format("%.2f", item.price.toDouble())}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp, horizontal = 10.dp),
                        colors = ButtonDefaults.buttonColors(Purple),
                        enabled = item.stock > 0,
                        onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Text(text = stringResource(id = R.string.add_to_cart), fontSize = 16.sp)
                    }
                }
            }
        })
}