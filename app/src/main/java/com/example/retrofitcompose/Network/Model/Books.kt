package com.example.retrofitcompose.Network.Model

data class Books(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)