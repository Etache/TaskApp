package com.example.taskapp_m4.ui.home.new_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskapp_m4.App
import com.example.taskapp_m4.databinding.FragmentNewTaskBinding
import com.example.taskapp_m4.ui.home.TaskModel


class NewTaskFragment : Fragment() {

    private lateinit var binding: FragmentNewTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        initListener()
        return binding.root

    }

    private fun initListener() {
        binding.btnSave.setOnClickListener {
//            setFragmentResult("new_task", bundleOf(
//                "title" to binding.etTitle.text.toString(),
//                "desc" to binding.etDesc.text.toString()
//            ))

            App.db.taskDao().insert(TaskModel(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            ))


            findNavController().navigateUp()
        }
    }
}