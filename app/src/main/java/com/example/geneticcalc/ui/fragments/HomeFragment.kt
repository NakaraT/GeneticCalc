package com.example.geneticcalc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geneticcalc.databinding.FragmentHomeBinding
import com.example.geneticcalc.ui.stateholder.viewModels.HomeViewModel

class HomeFragment : Fragment() {

    //Использование LiveData в коде
    private val _myLiveData = MutableLiveData<String>()
    val myLiveData: LiveData<String> = _myLiveData
    fun updateData(newData: String) {
        _myLiveData.value = newData
    }

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}