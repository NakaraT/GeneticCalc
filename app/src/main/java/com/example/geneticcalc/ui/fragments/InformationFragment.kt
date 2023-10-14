package com.example.geneticcalc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.example.geneticcalc.databinding.FragmentInformationBinding
import com.example.geneticcalc.ui.stateholder.viewModels.RelativesListItemViewModel
import com.example.geneticcalc.ui.stateholder.viewModels.RelativesListViewModel
import com.example.geneticcalc.ui.stateholder.viewModels.adapter.RelativesRecyclerAdapter

class InformationFragment : Fragment() {
    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val relativesListItemViewModel =
//            ViewModelProvider(this)[RelativesListItemViewModel::class.java]

        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = ViewModelProvider(this)[RelativesListViewModel::class.java]
        val adapter = RelativesRecyclerAdapter { id: Int -> openProfile(id) }
        binding.recycleItem.adapter = adapter
        model.listLiveData.observe(viewLifecycleOwner){
            adapter.setItems(it)
        }

        binding.add.setOnClickListener { v -> model.addRelative() }
    }

    private fun openProfile(id: Int) {
        findNavController(requireView()).navigate(InformationFragmentDirections.actionNavigationToProfile(id))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
