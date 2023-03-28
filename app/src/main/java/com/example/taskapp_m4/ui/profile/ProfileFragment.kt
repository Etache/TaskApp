package com.example.taskapp_m4.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taskapp_m4.databinding.FragmentProfileBinding
import com.example.taskapp_m4.utils.Preferences


class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding

    var mGetContent: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent()) { uri ->
        binding.imgProfile.setImageURI(uri)

        Glide
            .with(this)
            .load(uri)
            .circleCrop()
            .into(binding.imgProfile);
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            Preferences(requireContext()).setEditTextUsername(usname)

            binding.etUsername.setText("")
        }
    }

    private fun initViews() {
        val editTextUsername = Preferences(requireContext()).getEditTextValue()
        binding.etUsername.setText(editTextUsername)
        binding.tvUsername.text = editTextUsername
    }
}