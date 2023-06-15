package com.example.androidtraining.models

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Number,
    val title: String,
    val description: String,
    @SerializedName("short_description") val shortDescription: String,
    val stock: Int,
    val price: Number,
    val rating: Int,
    val image: String,
    val category: String,
)

