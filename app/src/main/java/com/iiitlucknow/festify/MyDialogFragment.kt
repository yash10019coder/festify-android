package com.iiitlucknow.festify

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.iiitlucknow.festify.ViewModels.home_view_model
import com.iiitlucknow.festify.data.my_events
import com.iiitlucknow.festify.databinding.FragmentMydialogBinding


class MyDialogFragment(myEvents: my_events, flag:Int) : DialogFragment() {
    val myflag=flag
    val new_event:my_events=myEvents
    private var _binding: FragmentMydialogBinding? = null
    private val binding get() = _binding!!
    lateinit var vm:home_view_model
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMydialogBinding.inflate(
            inflater, container, false
        )
        vm = ViewModelProvider(this).get(home_view_model::class.java)
        if(myflag==1){
            binding.headText.text="CONFIRM YOUR UNREGISTRATION"
        }
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.cancel.setOnClickListener {
                dismiss()
        }
        binding.confirm.setOnClickListener {
            if(myflag==0) {
                vm.addevent(new_event)
                Toast.makeText(activity,"You have successfully registered for the event",Toast.LENGTH_SHORT).show()
            }
            else{
                vm.deleteevent(new_event)
                Toast.makeText(activity,"You have successfully unregistered for the event",Toast.LENGTH_SHORT).show()
            }
            dismiss()
        }

        return binding.root
    }



}