package com.thahira.example.officeroomapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.thahira.example.officeroomapp.adapter.PeopleRecyclerViewAdapter
import com.thahira.example.officeroomapp.databinding.FragmentPeopleBinding
import com.thahira.example.officeroomapp.model.PeopleItem
import com.thahira.example.officeroomapp.util.UIState
import com.thahira.example.officeroomapp.viewmodel.OfficeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PeopleFragment : Fragment() {

    private  val binding: FragmentPeopleBinding by lazy {
        FragmentPeopleBinding.inflate(layoutInflater)
    }

    private lateinit var peopleRecyclerViewAdapter: PeopleRecyclerViewAdapter
    private val officeViewModel : OfficeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listOfPeople : MutableList<PeopleItem> = mutableListOf()
        peopleRecyclerViewAdapter = PeopleRecyclerViewAdapter(listOfPeople)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.fragPeople.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter =peopleRecyclerViewAdapter
        }

        officeViewModel.allPeople.observe(viewLifecycleOwner,::handleResult)
        officeViewModel.getAllPeople()
        binding.refreshItems.setOnRefreshListener {
            officeViewModel.getAllPeople()
        }

        return binding.root
    }

    fun handleResult(uiState: UIState?) {

        when(uiState){
            is UIState.LOADING ->{
                Toast.makeText(requireContext(),"LOADING...", Toast.LENGTH_LONG).show()
            }
            is UIState.SUCCESSPEOPLE ->{
                peopleRecyclerViewAdapter.setPeopleData(uiState.people)
            }
            is UIState.ERROR ->{
                Toast.makeText(requireContext(),"Error: "+ uiState.error.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PeopleFragment()
    }
}


