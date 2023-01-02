package com.iiitlucknow.android.festify.event

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.iiitlucknow.android.festify.databinding.FragmentMydialogBinding
import com.iiitlucknow.android.festify.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EventRegisterDialogFragment(eventsEntity: EventsEntity, flag: Int) : DialogFragment() {
    val myflag = flag
    val new_event: EventsEntity = eventsEntity
    private var _binding: FragmentMydialogBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMydialogBinding.inflate(
            inflater, container, false
        )
        if (myflag == 1) {
            binding.headText.text = "CONFIRM YOUR UNREGISTRATION"
        }
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.cancel.setOnClickListener {
            dismiss()
        }
        binding.confirm.setOnClickListener {
            if (myflag == 0) {
                homeViewModel.addevent(new_event)
                Toast.makeText(
                    activity,
                    "You have successfully registered for the event",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                homeViewModel.deleteevent(new_event)
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
