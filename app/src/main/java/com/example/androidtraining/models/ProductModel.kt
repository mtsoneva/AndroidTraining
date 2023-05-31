package com.example.androidtraining.models

data class Product(
    val id: Number,
    val title: String,
    val description: String,
    val short_description: String,
    val stock: Number,
    val price: Number,
    val rating: Number,
    val image: String,
    val category: String,
)

