package com.iiitlucknow.android.festify

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.iiitlucknow.android.festify.databinding.FragmentAddeventsBinding
import java.util.Calendar

class AddEventFragment : Fragment() {
    private var _binding: FragmentAddeventsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddeventsBinding.inflate(inflater, container, false)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.event_category,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.eventCategorySpinner.adapter = adapter
        }

        binding.eventDateEditText.setOnClickListener {
            showDatePicker(it as TextInputEditText)
        }
        binding.eventTimeEditText.setOnClickListener {
            showTimePicker(it as TextInputEditText)
        }
        binding.eventEndDateEditText.setOnClickListener {
            showDatePicker(it as TextInputEditText)
        }
        binding.eventEndTimeEditText.setOnClickListener {
            showTimePicker(it as TextInputEditText)
        }

        binding.submitEventBtn.setOnClickListener {
            /**
             * Get selected item in category spinner like this :
             * binding.eventCategorySpinner.selectedItem.toString()
             */
        }

        return binding.root
    }

    private fun showDatePicker(edittext: TextInputEditText) {
        val _calendar = Calendar.getInstance()
        val _year = _calendar.get(Calendar.YEAR)
        val _month = _calendar.get(Calendar.MONTH)
        val _day = _calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                edittext.setText("$day/$month/$year")
            },
            _year,
            _month,
            _day
        )
        datePicker.show()
    }

    private fun showTimePicker(edittext: TextInputEditText) {
        val _calendar = Calendar.getInstance()
        val _hour = _calendar.get(Calendar.HOUR_OF_DAY)
        val _minute = _calendar.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(
            requireContext(),
            TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                if (minute < 10) edittext.setText("$hour:0$minute")
                else edittext.setText("$hour:$minute")
            },
            _hour,
            _minute,
            false
        )
        timePicker.show()
    }
}
