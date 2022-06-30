package com.thahira.example.officeroomapp.util

import com.thahira.example.officeroomapp.model.PeopleItem
import com.thahira.example.officeroomapp.model.RoomsItem

sealed class UIState{
    class LOADING(): UIState()
    class SUCCESSPEOPLE(val people : List<PeopleItem>): UIState()
    class SUCCESSROOM(val room: List<RoomsItem>): UIState()
    class ERROR(val error: Throwable): UIState()
}
