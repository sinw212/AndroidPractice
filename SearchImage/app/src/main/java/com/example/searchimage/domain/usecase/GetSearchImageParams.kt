package com.example.searchimage.domain.usecase

data class GetSearchImageParams (
    val query: String,
    val sort: String = "recency",
    val page: Int = 1
)