package com.example.geneticcalc.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.example.geneticcalc.R
import com.example.geneticcalc.databinding.FragmentRelativesprofileBinding
import com.example.geneticcalc.ui.stateholder.viewModels.RelativesListItemViewModel

class RelativesProfile : Fragment() {

    private var binding: FragmentRelativesprofileBinding? = null
    private var viewModel: RelativesListItemViewModel? = null
    private var args: RelativesProfileArgs? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args = arguments?.let { RelativesProfileArgs.fromBundle(it) }
        binding = FragmentRelativesprofileBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RelativesListItemViewModel::class.java]
        this.args?.let { viewModel!!.getRelativesItem(it.id) }
        viewModel!!.relativesListItemLiveData.observe(viewLifecycleOwner) { relativesEntity ->
            binding!!.relativesType.setText(relativesEntity!!.relativesType)
            binding!!.eyeColor.setText(relativesEntity.eyeColor)
            binding!!.hairColor.setText(relativesEntity.hairColor)
            binding!!.dateofBirth.setText(relativesEntity.dateofBirth)
            binding!!.bloodType.setText(relativesEntity.bloodType)
            binding!!.relativeLogo.setImageResource(R.drawable.ic_baseline_man_2_24)
        }

        binding!!.delete.setOnClickListener {
            viewModel!!.deleteRelative(args!!.id)
            findNavController(requireView()).navigateUp()
        }

        binding!!.update.setOnClickListener {
            args!!.id
            viewModel!!.updateRelative(
                args!!.id,
                binding!!.relativesType.text.toString(),
                binding!!.eyeColor.text.toString(),
                binding!!.hairColor.text.toString(),
                binding!!.dateofBirth.text.toString(),
                binding!!.bloodType.text.toString()
            )
            Toast.makeText(context, "Данные обновлены!", Toast.LENGTH_SHORT).show()
        }

        binding!!.buttonBack.setOnClickListener {
            findNavController(requireView()).navigate(
                R.id.action_profile_to_navigation
            )
        }
    }
}
