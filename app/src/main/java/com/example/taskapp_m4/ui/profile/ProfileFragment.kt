package com.example.taskapp_m4.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.taskapp_m4.databinding.FragmentProfileBinding
import com.example.taskapp_m4.extensions.loadImage
import com.example.taskapp_m4.utils.Preferences


class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding

    private var mGetContent: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent()) { uri ->
        binding.imgProfile.setImageURI(uri)

        Preferences(requireContext()).imgUri = uri.toString()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        initListeners()
        initViews()
        return binding.root
    }

    private fun initListeners() {
        binding.imgProfile.setOnClickListener {
            mGetContent.launch("image/*")
        }
        binding.btnSave.setOnClickListener {
            val usname = binding.etUsername.text.toString()
            binding.tvUsername.setText(usname)
            binding.etUsername.setText("")

            Preferences(requireContext()).setEditTextUsername(usname)
        }
    }

    private fun initViews() {
        val editTextUsername = Preferences(requireContext()).getEditTextValue()
        binding.etUsername.setText(editTextUsername)
        binding.tvUsername.text = editTextUsername

        binding.imgProfile.loadImage(Preferences(requireContext()).imgUri.toString())
    }
}