package com.example.taskapp_m4.ui.auth

import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.taskapp_m4.databinding.FragmentAuthBinding
import com.example.taskapp_m4.extensions.showToast
import com.example.taskapp_m4.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    private var auth = FirebaseAuth.getInstance()
    private lateinit var correctCode: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)

        initView()
        initListeners()

        return binding.root
    }

    private fun initListeners() {

    }

    private fun initView() {
        binding.btnSend.setOnClickListener {
            if (binding.etPhone.text.isNotEmpty()){
                sendPhoneNumber()
            } else {
                binding.etPhone.error = "Поле пустое"
            }
        }

        binding.btnConfirm.setOnClickListener {
            sendCode()
        }
    }

    private fun sendPhoneNumber() {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(binding.etPhone.text.toString())
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                }

                override fun onVerificationFailed(firebaseException: FirebaseException) {
                    showToast(firebaseException.message.toString())
                }

                override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(verificationId, p1)

                    binding.etPhone.isVisible = false
                    binding.btnSend.isVisible = false

                    binding.etCode.isVisible = true
                    binding
                }

            })          
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun sendCode() {
        val credential = PhoneAuthProvider.getCredential(correctCode!!, binding.etCode.text.toString())
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d("ololo", "signInWithCredential:success")
                    findNavController().navigate(R.id.navigation_home)

                    val user = task.result?.user
                } else {
                    Log.w("ololo", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        showToast("Verification code is incorrect")
                    }
                }
            }
    }

}