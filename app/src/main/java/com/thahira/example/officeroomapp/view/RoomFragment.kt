package com.thahira.example.officeroomapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.thahira.example.officeroomapp.adapter.RoomRecyclerViewAdapter
import com.thahira.example.officeroomapp.databinding.FragmentRoomBinding
import com.thahira.example.officeroomapp.model.RoomsItem
import com.thahira.example.officeroomapp.util.UIState
import com.thahira.example.officeroomapp.viewmodel.OfficeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomFragment : Fragment() {

    private val binding: FragmentRoomBinding by lazy{
        FragmentRoomBinding.inflate(layoutInflater)
    }

    private lateinit var roomRecyclerViewAdapter: RoomRecyclerViewAdapter
    private val officeViewModel : OfficeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listofRooms: MutableList<RoomsItem> = mutableListOf()
        roomRecyclerViewAdapter = RoomRecyclerViewAdapter(listofRooms)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.fragRoom.apply {
            layoutManager = GridLayoutManager(context,2) //or give requireContext()
            adapter= roomRecyclerViewAdapter
        }

        officeViewModel.allRooms.observe(viewLifecycleOwner,::handleResult)
        officeViewModel.getAllRooms()
        binding.refreshItems.setOnRefreshListener {
            officeViewModel.getAllRooms()
        }

        return binding.root
    }

    fun handleResult(uiState: UIState?) {

        when(uiState){
            is UIState.LOADING ->{
                Toast.makeText(requireContext(),"LOADING...", Toast.LENGTH_LONG).show()
            }
            is UIState.SUCCESSROOM ->{
                roomRecyclerViewAdapter.setRoomData(uiState.room)
            }
            is UIState.ERROR ->{
                Toast.makeText(requireContext(),"Error: "+ uiState.error.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {

        fun newInstance() = RoomFragment()

    }
}