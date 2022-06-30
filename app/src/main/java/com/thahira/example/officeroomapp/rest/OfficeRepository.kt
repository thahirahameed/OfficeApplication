package com.thahira.example.officeroomapp.rest

import com.thahira.example.officeroomapp.util.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.IllegalStateException

interface OfficeRepository {
    val peopleList: StateFlow<UIState>
    suspend fun getPeopleList()

    val roomList: StateFlow<UIState>
    suspend fun getRoomList()
}

class OfficeRepositoryImpl(
    private val officeApi: OfficeApi
) : OfficeRepository{

    private val _peopleList : MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())
    override val peopleList: StateFlow<UIState>
        get() = _peopleList


    private val _roomList : MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())
    override  val roomList : StateFlow<UIState>
            get() = _roomList

    override suspend fun getPeopleList() {
        try{
            val response = officeApi.getPeopleData()
            if(response.isSuccessful) {
                response.body()?.let {
                    _peopleList.value = UIState.SUCCESSPEOPLE(it)
                } ?: run {
                    _peopleList.value =
                        UIState.ERROR(IllegalStateException("List is coming as null"))
                }
            }else
            {
                _peopleList.value = UIState.ERROR(Exception(response.errorBody()?.toString()))
            }

        }catch(e: Exception){
            _peopleList.value = UIState.ERROR(e)
        }
    }


    override suspend fun getRoomList() {
        try{
            val response = officeApi.getRoomData()
            if(response.isSuccessful) {
                response.body()?.let {
                    _roomList.value = UIState.SUCCESSROOM(it)
                } ?: run {
                    _roomList.value =
                        UIState.ERROR(IllegalStateException("List is coming as null"))
                }
            }else
            {
                _roomList.value = UIState.ERROR(Exception(response.errorBody()?.toString()))
            }

        }catch(e: Exception){
            _roomList.value = UIState.ERROR(e)
        }
    }
}