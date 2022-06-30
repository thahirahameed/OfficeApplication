package com.thahira.example.officeroomapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thahira.example.officeroomapp.rest.OfficeRepository
import com.thahira.example.officeroomapp.util.UIState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class OfficeViewModel(
    private val officeRepository: OfficeRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob()+ ioDispatcher)
) : CoroutineScope by coroutineScope, ViewModel(){

    private var _allPeople: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
    val allPeople: LiveData<UIState> get() = _allPeople

    private var _allRooms: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
    val allRooms: LiveData<UIState> get() = _allRooms

    fun getAllPeople(){
        collectPeopleInfo()
        launch{
            officeRepository.getPeopleList()
        }
    }

    fun getAllRooms(){
        collectRoomInfo()
        launch{
            officeRepository.getRoomList()
        }
    }

    private fun collectRoomInfo() {
        launch{
            officeRepository.roomList.collect{ uiState->
                when(uiState){
                    is UIState.LOADING -> {_allRooms.postValue(uiState)}
                    is UIState.SUCCESSROOM ->{ _allRooms.postValue(uiState)}
                    is UIState.ERROR ->{ _allRooms.postValue(uiState)}
                }
            }
        }
    }

    private fun collectPeopleInfo() {
        launch{
            officeRepository.peopleList.collect{ uiState->
                when(uiState){
                    is UIState.LOADING->{ _allPeople.postValue(uiState)}
                    is UIState.SUCCESSPEOPLE->{ _allPeople.postValue(uiState)}
                    is UIState.ERROR -> {_allPeople.postValue(uiState)}
                }

            }
        }
    }
}