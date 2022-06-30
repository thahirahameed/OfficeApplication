package com.thahira.example.officeroomapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoomsItem(
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "isOccupied")
    val isOccupied: Boolean,
    @Json(name = "maxOccupancy")
    val maxOccupancy: Int
)