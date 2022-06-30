package com.thahira.example.officeroomapp.rest

import com.thahira.example.officeroomapp.model.PeopleItem
import com.thahira.example.officeroomapp.model.RoomsItem
import retrofit2.Response
import retrofit2.http.GET

interface OfficeApi {

    @GET(PEOPLE)
    suspend fun getPeopleData():Response<List<PeopleItem>>

    @GET(ROOMS)
    suspend fun getRoomData():Response<List<RoomsItem>>

    companion object{
        const val  BASE_URL = "https://61e947967bc0550017bc61bf.mockapi.io/api/v1/"
        private const val PEOPLE = "people"
        private const val ROOMS = "rooms"
    }
}