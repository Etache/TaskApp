package com.example.taskapp_m4.ui.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.taskapp_m4.R
import com.example.taskapp_m4.databinding.FragmentOnBoardPageBinding
import com.example.taskapp_m4.utils.Preferences

class OnBoardPageFragment(
    private var onNextClick: () -> Unit,
    private var onSkipClick: () -> Unit
) : Fragment() {

    private lateinit var binding : FragmentOnBoardPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPageBinding.inflate(inflater, container, false)
        initViews()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.btnStart.setOnClickListener {
            findNavController().navigateUp()
            Preferences(requireContext()).setBoardingShowed(true)
        }

        binding.btnNext.setOnClickListener {
            onNextClick.invoke()
        }

        binding.btnSkip.setOnClickListener {
            onSkipClick.invoke()
        }
    }

    private fun initViews() {
        @Suppress("DEPRECATION") val data = arguments?.getSerializable("onBoard") as BoardModel
        binding.imgBoard.setImageResource(data.img)
        binding.tvTitleBoard.text = data.title
        binding.tvDescBoard.text = data.description

        binding.btnSkip.isVisible = data.isLast == false
        binding.btnNext.isVisible = data.isLast == false
        binding.btnStart.isVisible = data.isLast == true
    }
}