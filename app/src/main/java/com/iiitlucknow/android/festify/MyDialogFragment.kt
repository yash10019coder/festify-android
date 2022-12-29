package com.iiitlucknow.android.festify

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.iiitlucknow.android.festify.data_classes.regEvent
import com.iiitlucknow.android.festify.databinding.FragmentMydialogBinding
import com.iiitlucknow.android.festify.viewModels.RegisterViewModel
import com.iiitlucknow.android.festify.viewModels.UserViewModel

class MyDialogFragment(id:String, flag: Int) : DialogFragment() {
    val myflag = flag
    val _id: String = id
    lateinit var registerViewModel: RegisterViewModel
    lateinit var userviewModel: UserViewModel
    private var _binding: FragmentMydialogBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMydialogBinding.inflate(
            inflater, container, false
        )
        registerViewModel=ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(RegisterViewModel::class.java)
         userviewModel=ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(UserViewModel::class.java)
        if (myflag == 1) {
            binding.headText.text = "CONFIRM YOUR UNREGISTRATION"
        }
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.cancel.setOnClickListener {
            dismiss()
        }
        binding.confirm.setOnClickListener {
            if (myflag == 0) {
                userviewModel.myUserData.observe(viewLifecycleOwner){
                    registerViewModel.pushPost(regEvent(it.message.userName,_id))
                }

                registerViewModel.regResponse.observe(viewLifecycleOwner){
                    if(it.isSuccessful){
                        Toast.makeText(requireContext(),it.message(),Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(requireContext(),it.errorBody().toString(),Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(
                    activity,
                    "You have successfully unregistered for the event",
                    Toast.LENGTH_SHORT
                ).show()
            }
            dismiss()
        }

        return binding.root
    }
}
