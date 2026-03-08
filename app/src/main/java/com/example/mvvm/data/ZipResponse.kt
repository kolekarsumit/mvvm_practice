package com.example.mvvm.data

data class ZipResponse(
    val country: String,
    val places: List<Place>
)

data class Place(
    val  name: String,
    val longitude: String,
    val latitude: String,
    val state: String
)